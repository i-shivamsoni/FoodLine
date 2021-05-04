package com.example.foodline.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodline.databinding.FragmentCartBinding;
import com.example.foodline.model.MenuItem;
import com.example.foodline.viewmodel.CartViewModel;

import java.text.DecimalFormat;

public class CartFragment extends Fragment {

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
        ((BaseActivity) requireActivity()).setAppBar(1);

        cartViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(CartViewModel.class);

        adapter = new CartItemAdapter((button, cartItem) -> {
            for(MenuItem menuItem: cartViewModel.getMenuItems().getValue()){
                if(menuItem.getId() == cartItem.getId()){
                    menuItem.setCounterInCart(Integer.parseInt(button.getNumber()));
                    cartViewModel.update(menuItem);
                }
            }
            adapter.notifyDataSetChanged();
        });

        binding.cartItemList.setAdapter(adapter);

        observerData();
    }

    private void observerData() {
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), cartList -> {
            if(cartList != null){
                adapter.submitList(cartList);
                adapter.notifyDataSetChanged();
                if(cartList.size() == 0){
                    binding.emptyCartText.setVisibility(View.VISIBLE);
                    binding.cartItemList.setVisibility(View.GONE);
                    binding.payBtn.setVisibility(View.GONE);
                    binding.dividerView.setVisibility(View.GONE);
                    binding.grandTotalLayout.setVisibility(View.GONE);
                    binding.orderingForLayout.setVisibility(View.GONE);
                }else{
                    binding.emptyCartText.setVisibility(View.GONE);
                    binding.cartItemList.setVisibility(View.VISIBLE);
                    binding.payBtn.setVisibility(View.VISIBLE);
                    binding.dividerView.setVisibility(View.VISIBLE);
                    binding.grandTotalLayout.setVisibility(View.VISIBLE);
                    binding.orderingForLayout.setVisibility(View.VISIBLE);
                    grandTotal = 0f;
                    for(MenuItem cartItem: cartList){
                        grandTotal += (Float.parseFloat(cartItem.getPrice())*cartItem.getCounterInCart());
                    }
                    binding.grandTotalText.setText(String.format("Rs. %s", new DecimalFormat("#.#").format(grandTotal)));
                }
            }
        });
    }

}