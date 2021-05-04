package com.example.foodline.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodline.R;
import com.example.foodline.databinding.FragmentMenuBinding;
import com.example.foodline.databinding.MenuItemBottomSheetLayoutBinding;
import com.example.foodline.model.FoodApiStatus;
import com.example.foodline.model.MenuItem;
import com.example.foodline.utils.ScreenUtil;
import com.example.foodline.utils.SpaceItemDecorationUtil;
import com.example.foodline.viewmodel.MenuViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {

    private FragmentMenuBinding binding;
    private ImageView screenIcon;
    private TextView screenTitleText;

    private MenuItemAdapter adapter;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();
    private List<MenuItem> searchedItems = new ArrayList<>();

    private BottomSheetDialog bottomSheetDialog;
    private MenuViewModel menuViewModel;

    public MenuFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) requireActivity()).setAppBar(0);

        menuViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(MenuViewModel.class);

        adapter = new MenuItemAdapter(new MenuItemAdapter.MyAdapterListener() {
            @Override
            public void onAddBtnClicked(View view, int position) {
                changeState((LottieAnimationView) view, position);
            }

            @Override
            public void onMenuItemClicked(View view, int position) {
                MenuItem menuItem;
                if(menuViewModel.getSearchedMenuItems().getValue() == null) {
                     menuItem = menuItems.get(position);
                }else{
                    menuItem = searchedItems.get(position);
                }
                Log.d("tzuyu", menuItem.toString());
                showBottomSheetDialog(menuItem);
            }
        });

        binding.menuItemList.setAdapter(adapter);
        binding.menuItemList.addItemDecoration(new SpaceItemDecorationUtil((int) ScreenUtil.dptoPx(requireContext(), 12f)));

        setListeners();
        observeData();
    }

    private void setListeners() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(menuViewModel.getMenuItems().getValue() != null && !menuViewModel.getMenuItems().getValue().isEmpty()){
                    searchQueryInList(menuViewModel.getMenuItems().getValue(), query);
                }else if(query.isEmpty()){
                    adapter.submitList(menuViewModel.getMenuItems().getValue());
                    adapter.notifyDataSetChanged();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(menuViewModel.getMenuItems().getValue() != null && !menuViewModel.getMenuItems().getValue().isEmpty()){
                    searchQueryInList(menuViewModel.getMenuItems().getValue(), query);
                }else if(query.isEmpty()){
                    adapter.submitList(menuViewModel.getMenuItems().getValue());
                    adapter.notifyDataSetChanged();

                    menuViewModel.getSearchedMenuItems().setValue(null);
                }
                return true;
            }
        });

        binding.refreshLayout.setOnRefreshListener(() -> {
            menuViewModel.refreshMenu();
            binding.refreshLayout.setRefreshing(false);
        });
    }

    private void searchQueryInList(List<MenuItem> value, String query) {
        List<MenuItem> searchedItems = new ArrayList<>();
        query = query.toLowerCase();

        for(MenuItem menuItem : value){
            if(menuItem.getCategory().toLowerCase().contains(query) || menuItem.getName().toLowerCase().contains(query)){
                searchedItems.add(menuItem);
            }
        }
        menuViewModel.getSearchedMenuItems().setValue(searchedItems);
        adapter.submitList(searchedItems);
        adapter.notifyDataSetChanged();
    }

    private void observeData() {
        menuViewModel.getMenuItems().observe(getViewLifecycleOwner(), listData -> {
            if(listData != null){
                menuItems = listData;
                adapter.submitList(listData);
            }
        });

        menuViewModel.getSearchedMenuItems().observe(getViewLifecycleOwner(), searchList -> {
            if(searchList != null){
                searchedItems = searchList;
            }
        });

        menuViewModel.getFoodApiStatus().observe(getViewLifecycleOwner(), foodApiStatus -> {
            if(foodApiStatus != null){
                if (foodApiStatus == FoodApiStatus.LOADING) {
                    binding.loadingView.setVisibility(View.VISIBLE);
                    binding.menuItemList.setVisibility(View.GONE);
                    binding.noInternetText.setVisibility(View.GONE);
                    binding.failureText.setVisibility(View.GONE);
                } else if (foodApiStatus == FoodApiStatus.NO_INTERNET) {
                    binding.loadingView.setVisibility(View.GONE);
                    binding.menuItemList.setVisibility(View.GONE);
                    binding.noInternetText.setVisibility(View.VISIBLE);
                    binding.failureText.setVisibility(View.GONE);
                } else if (foodApiStatus == FoodApiStatus.SUCCESS) {
                    binding.loadingView.setVisibility(View.GONE);
                    binding.menuItemList.setVisibility(View.VISIBLE);
                    binding.noInternetText.setVisibility(View.GONE);
                    binding.failureText.setVisibility(View.GONE);
                } else {
                    binding.loadingView.setVisibility(View.GONE);
                    binding.menuItemList.setVisibility(View.GONE);
                    binding.noInternetText.setVisibility(View.GONE);
                    binding.failureText.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void changeState(LottieAnimationView toggle, int position) {
        MenuItem menuItem;
        if(menuViewModel.getSearchedMenuItems().getValue() == null) {
            menuItem = menuItems.get(position);
        }else{
            menuItem = searchedItems.get(position);
        }

        if (menuItem.getCounterInCart() == 0) {
            toggle.setSpeed(3f);
            toggle.playAnimation();

            Drawable[] drawables = {ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_lottie_back),ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_lottie_selected_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);

            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);

            menuItem.setCounterInCart(1);
            menuViewModel.update(menuItem);
            Toast.makeText(requireContext(), "Added to Cart :)", Toast.LENGTH_SHORT).show();
        } else {
            toggle.setSpeed(-3f);
            toggle.playAnimation();

            Drawable[] drawables = {ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_lottie_selected_back),ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_lottie_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);

            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);

            menuItem.setCounterInCart(0);
            menuViewModel.update(menuItem);
            Toast.makeText(requireContext(), "Removed from Cart :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void showBottomSheetDialog(MenuItem menuItem) {
        bottomSheetDialog = new BottomSheetDialog(requireContext());
        MenuItemBottomSheetLayoutBinding bottomSheetLayoutBinding = MenuItemBottomSheetLayoutBinding.inflate(getLayoutInflater());

        bottomSheetLayoutBinding.menuItemNameAndPrice.setText(String.format("%s : Rs. %s", menuItem.getName(), menuItem.getPrice()));
        bottomSheetLayoutBinding.menuItemCategory.setText(menuItem.getCategory());

        bottomSheetLayoutBinding.menuItemAddBtn.setOnClickListener(v ->{
            menuItem.setCounterInCart(1);
            menuViewModel.update(menuItem);
            adapter.notifyDataSetChanged();
            Toast.makeText(requireContext(), "Added to Cart :)", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.setContentView(bottomSheetLayoutBinding.getRoot());
        bottomSheetDialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
        }
    }

}