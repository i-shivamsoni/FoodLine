package com.example.foodline.ui.main.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodline.databinding.FragmentCartBinding;
import com.example.foodline.model.MenuItem;
import com.example.foodline.ui.main.MainActivity;

import java.text.DecimalFormat;

public class CartFragment extends Fragment {

    private static final String TAG = "CartFragment";

    private FragmentCartBinding binding;

    private CartItemAdapter adapter;
    private CartViewModel cartViewModel;
    private Float grandTotal = 0f;

    public CartFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(CartViewModel.class);

        adapter = new CartItemAdapter((button, cartItem) -> {
            for(MenuItem menuItem: cartViewModel.getCartState().getValue().data){
                if(menuItem.getId() == cartItem.getId()){
                    int counterInCart = Integer.parseInt(button.getNumber());
                    menuItem.setCounterInCart(counterInCart);

                    cartViewModel.update(menuItem);
                    cartViewModel.getCartMenuItems();
                }
            }
            adapter.notifyDataSetChanged();
        });

        binding.cartItemList.setAdapter(adapter);

        cartViewModel.getCartMenuItems();

        observerData();
    }

    private void observerData() {
        cartViewModel.getCartState().observe(getViewLifecycleOwner(), listCartState -> {
            if(listCartState != null){
                switch (listCartState.status) {

                    case EMPTY: {
                        showEmptyText(true);
                        showLayout(false);
                        break;
                    }

                    case HAS_ITEMS: {
                        showEmptyText(false);
                        adapter.submitList(listCartState.data);
                        showLayout(true);
                        break;
                    }

                    case ERROR: {
                        showEmptyText(true);
                        showLayout(false);
                        Log.d(TAG, "observerData: " + listCartState.throwable);
                        break;
                    }
                }
            }
        });

        cartViewModel.getGrandTotal().observe(getViewLifecycleOwner(), grandTotal -> {
            if(grandTotal != null){
                binding.grandTotalText.setText(String.format("Rs. %s", grandTotal));
            }
        });
    }

    private void showLayout(boolean isDisplayed) {
        if(isDisplayed){
            binding.dividerView.setVisibility(View.VISIBLE);
            binding.grandTotalLayout.setVisibility(View.VISIBLE);
            binding.orderingForLayout.setVisibility(View.VISIBLE);
            binding.payBtn.setVisibility(View.VISIBLE);
            binding.cartItemList.setVisibility(View.VISIBLE);
        }else{
            binding.dividerView.setVisibility(View.GONE);
            binding.grandTotalLayout.setVisibility(View.GONE);
            binding.orderingForLayout.setVisibility(View.GONE);
            binding.payBtn.setVisibility(View.GONE);
            binding.cartItemList.setVisibility(View.GONE);
        }
    }

    private void showEmptyText(boolean isDisplayed) {
        if(isDisplayed)
            binding.emptyCartText.setVisibility(View.VISIBLE);
        else
            binding.emptyCartText.setVisibility(View.GONE);
    }

}