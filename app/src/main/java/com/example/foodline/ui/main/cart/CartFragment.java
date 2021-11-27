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
import android.widget.Toast;

import com.example.foodline.databinding.FragmentCartBinding;
import com.example.foodline.model.MenuItem;
import com.example.foodline.model.Order;
import com.example.foodline.model.OrderItem;
import com.example.foodline.ui.main.MainActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private static final String TAG = "CartFragment";

    private FragmentCartBinding binding;

    private CartItemAdapter adapter;
    private CartViewModel cartViewModel;
    private Float grandTotal = 0f;

    private List<MenuItem> cartItems = new ArrayList<>();

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
        setListeners();
    }

    private void setListeners() {
        binding.payBtn.setOnClickListener(v -> {
            if (cartItems != null && !cartItems.isEmpty() && grandTotal != 0f) {
                List<OrderItem> orderItems = new ArrayList<>();

                for(MenuItem menuItem: cartItems) {
                    orderItems.add(new OrderItem(menuItem.getId(), menuItem.getCounterInCart(), Float.parseFloat(menuItem.getPrice())));
                }

                Order order = new Order(orderItems, "card", 0f, grandTotal);
                cartViewModel.addOrder(order);
            }
        });
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
                        cartItems = listCartState.data;
                        adapter.submitList(cartItems);
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

        cartViewModel.getGrandTotal().observe(getViewLifecycleOwner(), gt -> {
            if(gt != null){
                grandTotal = gt;
                binding.grandTotalText.setText(String.format("Rs. %s", gt));
            }
        });

        cartViewModel.getOrderStateLiveData().observe(getViewLifecycleOwner(), orderState -> {
            if (orderState != null) {
                switch (orderState.status) {
                    case LOADING: {
                        binding.loadingView.setVisibility(View.VISIBLE);
                        break;
                    }

                    case SUCCESS: {
                        binding.loadingView.setVisibility(View.GONE);
                        binding.grandTotalText.setVisibility(View.GONE);
                        adapter.submitList(new ArrayList<>());
                        Toast.makeText(getContext(), "Order placed", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case ERROR: {
                        binding.loadingView.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Something went wrong!! Please try again", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
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