package com.example.foodline.ui.main.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodline.R;
import com.example.foodline.databinding.FragmentSearchBinding;
import com.example.foodline.databinding.LayoutMenuItemBottomSheetBinding;
import com.example.foodline.model.MenuItem;
import com.example.foodline.ui.main.MainActivity;
import com.example.foodline.ui.main.menu.MenuItemAdapter;
import com.example.foodline.ui.main.menu.MenuViewModel;
import com.example.foodline.utils.ScreenUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding binding;
    private InputMethodManager imm;

    private MenuItemAdapter adapter;
    private Context context;

    private BottomSheetDialog bottomSheetDialog;
    private SearchViewModel searchViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = requireContext();
        searchViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(SearchViewModel.class);

        if(binding.searchText.requestFocus()) {
            imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.searchText, InputMethodManager.SHOW_IMPLICIT);
        }

        adapter = new MenuItemAdapter(new MenuItemAdapter.MyAdapterListener() {
            @Override
            public void onAddBtnClicked(View view, int position) {
                changeState((LottieAnimationView) view, position);
            }

            @Override
            public void onMenuItemClicked(View view, int position) {
                showBottomSheetDialog(searchViewModel.getSearchState().getValue().data.get(position));
            }
        });

        binding.serachItemList.setAdapter(adapter);

        setListeners();
        observeData();
    }

    private void observeData() {

        searchViewModel.getSearchState().observe(getViewLifecycleOwner(), listSearchState -> {
            if(listSearchState != null){

                switch (listSearchState.status){

                    case LOADING:{
                        Log.d(TAG, "observeData: Loading");
                        break;
                    }

                    case EMPTY:{
                        Log.d(TAG, "observeData: Empty");
                        adapter.submitList(new ArrayList<>());
                        break;
                    }

                    case HAS_ITEMS:{
                        Log.d(TAG, "observeData: Has Items" + listSearchState.data);
                        adapter.submitList(listSearchState.data);
                        break;
                    }

                    case ERROR:{
                        Log.d(TAG, "observeData: Error" + listSearchState.throwable);
                        break;
                    }
                }
            }
        });
    }

    private void setListeners() {

        binding.backBtn.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
            binding.searchText.clearFocus();
        });

        binding.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    binding.clearBtn.setVisibility(View.VISIBLE);
                }else {
                    binding.clearBtn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0){
                    searchViewModel.getMenu(s.toString().trim());
                }else{
                    adapter.submitList(new ArrayList<>());
                }
            }
        });

        binding.clearBtn.setOnClickListener(v -> {
            binding.searchText.clearFocus();
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }

    private void changeState(LottieAnimationView toggle, int position) {
        MenuItem menuItem = searchViewModel.getSearchState().getValue().data.get(position);

        if (menuItem.getCounterInCart() == 0) {
            toggle.setSpeed(3f);
            toggle.playAnimation();

            Drawable[] drawables = {ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_back),ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_selected_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);

            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);

            menuItem.setCounterInCart(1);
            searchViewModel.update(menuItem);
            Toast.makeText(context, "Added to Cart :)", Toast.LENGTH_SHORT).show();
        } else {
            toggle.setSpeed(-3f);
            toggle.playAnimation();

            Drawable[] drawables = {ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_selected_back),ContextCompat.getDrawable(context, R.drawable.ic_add_lottie_back)};
            TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);

            toggle.setBackground(transitionDrawable);
            transitionDrawable.startTransition(400);

            menuItem.setCounterInCart(0);
            searchViewModel.update(menuItem);
            Toast.makeText(context, "Removed from Cart :(", Toast.LENGTH_SHORT).show();
        }
    }

    private void showBottomSheetDialog(MenuItem menuItem) {
        bottomSheetDialog = new BottomSheetDialog(context);
        LayoutMenuItemBottomSheetBinding bottomSheetLayoutBinding = LayoutMenuItemBottomSheetBinding.inflate(getLayoutInflater());

        bottomSheetLayoutBinding.menuItemNameAndPrice.setText(String.format("%s : Rs. %s", menuItem.getName(), menuItem.getPrice()));
        bottomSheetLayoutBinding.menuItemCategory.setText(menuItem.getCategory());

        bottomSheetLayoutBinding.menuItemAddBtn.setOnClickListener(v ->{
            menuItem.setCounterInCart(1);
            searchViewModel.update(menuItem);
            adapter.notifyDataSetChanged();
            Toast.makeText(context, "Added to Cart :)", Toast.LENGTH_SHORT).show();
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
