package com.example.foodline.ui.main.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodline.R;
import com.example.foodline.databinding.FragmentMenuBinding;
import com.example.foodline.databinding.LayoutMenuItemBottomSheetBinding;
import com.example.foodline.model.MenuItem;
import com.example.foodline.utils.ScreenUtil;
import com.example.foodline.utils.SpaceItemDecorationUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

public class MenuFragment extends Fragment {

    private FragmentMenuBinding binding;
    private Context context;

    private MenuItemAdapter adapter;

    private BottomSheetDialog bottomSheetDialog;
    private MenuViewModel menuViewModel;
    private final String TAG = this.getClass().getSimpleName();

    public MenuFragment() { }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = requireContext();
        menuViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(MenuViewModel.class);

        adapter = new MenuItemAdapter(new MenuItemAdapter.MyAdapterListener() {
            @Override
            public void onAddBtnClicked(View view, int position) {
                changeState((LottieAnimationView) view, position);
            }

            @Override
            public void onMenuItemClicked(View view, int position) {
                showBottomSheetDialog(menuViewModel.getMenuState().getValue().data.get(position));
            }
        });

        binding.menuItemList.setAdapter(adapter);
        binding.menuItemList.addItemDecoration(new SpaceItemDecorationUtil((int) ScreenUtil.dptoPx(context, 12f)));

        menuViewModel.getMenu();

        setListeners();
        observeData();
    }

    private void setListeners() {

        binding.searchView.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(MenuFragmentDirections.actionMenuFragmentToSearchFragment());
        });


        binding.refreshLayout.setOnRefreshListener(() -> {
            menuViewModel.refreshMenu();
            binding.refreshLayout.setRefreshing(false);
        });

        binding.filterIcon.setOnClickListener(v -> showFilterAlertDialog());
    }

    private void observeData() {

        menuViewModel.getMenuState().observe(getViewLifecycleOwner(), listMenuState -> {
            switch (listMenuState.status){

                case LOADING:{
                    showProgressBar(true);
                    showErrorText(false);
                    showList(false);
                    break;
                }

                case SUCCESS:{
                    showProgressBar(false);
                    showErrorText(false);
                    showList(true);
                    adapter.submitList(listMenuState.data);
                    break;
                }

                case ERROR:{
                    showProgressBar(false);
                    showErrorText(true);
                    showList(false);
                    showToast("Check your Internet connection and try again :(");
                    break;
                }
            }
        });
    }

    private void showFilterAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.MaterialThemeDialog);

        String[] items = {"None", "Bevarages", "Chinese", "Desert", "Fast Food"};
        int checkedItem = 0;
        alertDialog.setTitle("Filter");
        alertDialog.setSingleChoiceItems(items, checkedItem, (dialog, which) -> {
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void showList(boolean isDisplayed) {
        if(isDisplayed) {
            binding.menuItemList.setVisibility(View.VISIBLE);
            return;
        }
        binding.menuItemList.setVisibility(View.GONE);
    }

    private void showErrorText(boolean isDisplayed) {
        if(isDisplayed) {
            binding.noInternetText.setVisibility(View.VISIBLE);
            return;
        }
        binding.noInternetText.setVisibility(View.GONE);
    }

    private void showToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    private void showProgressBar(boolean isDisplayed) {
        if(isDisplayed) {
            binding.loadingView.setVisibility(View.VISIBLE);
            return;
        }
        binding.loadingView.setVisibility(View.GONE);
    }

    private void changeState(LottieAnimationView toggle, int position) {
        MenuItem menuItem = menuViewModel.getMenuState().getValue().data.get(position);

        if (menuItem.getCounterInCart() == 0) {
            menuItem.setCounterInCart(1);
            menuViewModel.update(menuItem);

            toggle.setSpeed(3f);
            toggle.playAnimation();

            Drawable[] drawables = {ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_back),ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_selected_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);

            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);

            showToast("Added to Cart :)");
        } else {
            menuItem.setCounterInCart(0);
            menuViewModel.update(menuItem);

            toggle.setSpeed(-3f);
            toggle.playAnimation();

            Drawable[] drawables = {ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_selected_back),ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);

            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);

            showToast("Removed from Cart :(");
        }
    }

    private void showBottomSheetDialog(MenuItem menuItem) {
        bottomSheetDialog = new BottomSheetDialog(context);
        LayoutMenuItemBottomSheetBinding bottomSheetLayoutBinding = LayoutMenuItemBottomSheetBinding.inflate(getLayoutInflater());

        bottomSheetLayoutBinding.menuItemNameAndPrice.setText(String.format("%s : Rs. %s", menuItem.getName(), menuItem.getPrice()));
        bottomSheetLayoutBinding.menuItemCategory.setText(menuItem.getCategory());

        bottomSheetLayoutBinding.menuItemAddBtn.setOnClickListener(v ->{
            menuItem.setCounterInCart(1);
            menuViewModel.update(menuItem);
            adapter.notifyDataSetChanged();
            showToast("Added to Cart :)");
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