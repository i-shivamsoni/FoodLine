package com.example.foodline.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodline.databinding.FragmentCartBinding;
import com.example.foodline.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    private CartItemAdapter adapter;
    private List<MenuItem> cartItems = new ArrayList<MenuItem>();

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

        cartItems.add(new MenuItem("Pizza", "Fast Food", "200",""));
        cartItems.add(new MenuItem("Burger", "Fast Food", "50",""));

        adapter = new CartItemAdapter(cartItems);

        binding.cartItemList.setAdapter(adapter);


    }
}