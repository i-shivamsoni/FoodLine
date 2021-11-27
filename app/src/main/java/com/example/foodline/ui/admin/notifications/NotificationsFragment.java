package com.example.foodline.ui.admin.notifications;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodline.databinding.FragmentNotificationsBinding;
import com.example.foodline.model.Order;
import com.example.foodline.model.OrderItem;
import com.example.foodline.model.User;
import com.example.foodline.utils.ScreenUtil;
import com.example.foodline.utils.SpaceItemDecorationUtil;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private NotificationsViewModel viewModel;
    private final String TAG = "NotificationsFragment";

    private OrderItemAdapter adapter;

    public NotificationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NotificationsViewModel.class);
        viewModel.getAcceptedOrders();

        adapter = new OrderItemAdapter(id -> {
            viewModel.acceptOrder(id);
        });

        List<Order> orders = new ArrayList<>();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(1, "Samosa", 4, "20.00", ""));
        orderItems.add(new OrderItem(2, "Ice Cream", 4, "50.00", ""));

        orders.add(new Order(1, orderItems, new User("Test1", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(2, orderItems, new User("Test2", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(3, orderItems, new User("Test3", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(4, orderItems, new User("Test4", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(5, orderItems, new User("Test5", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(6, orderItems, new User("Test6", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(7, orderItems, new User("Test7", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(8, orderItems, new User("Test8", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(9, orderItems, new User("Test9", "test@gmail.com"), "card", false, "0.00", "70"));
        orders.add(new Order(10, orderItems, new User("Test10", "test@gmail.com"), "card", false, "0.00", "70"));

        adapter.submitList(orders);

        binding.orderList.setAdapter(adapter);
        binding.orderList.addItemDecoration(new SpaceItemDecorationUtil((int) ScreenUtil.dptoPx(getContext(), 12f)));
//        viewModel.getAcceptedOrders();
//        observeData();
    }

    private void observeData() {
        viewModel.getOrderState().observe(getViewLifecycleOwner(), orderState -> {
            if(orderState != null) {
                switch (orderState.status) {
                    case LOADING: {
                        binding.loadingView.setVisibility(View.VISIBLE);
                        binding.errorText.setVisibility(View.GONE);
                        binding.orderList.setVisibility(View.GONE);
                        break;
                    }
                    case SUCCESS: {
                        if (orderState.data != null && !orderState.data.isEmpty()){
                            binding.loadingView.setVisibility(View.GONE);
                            binding.orderList.setVisibility(View.GONE);
                            binding.errorText.setVisibility(View.VISIBLE);
                        } else {
                            adapter.submitList(orderState.data);

                            binding.loadingView.setVisibility(View.GONE);
                            binding.orderList.setVisibility(View.VISIBLE);
                            binding.errorText.setVisibility(View.GONE);
                        }
                    }
                    case ERROR: {
                        if (orderState.throwable != null) {
                            Log.d(TAG, "Error: " + orderState.throwable.getMessage());
                        }
                        binding.loadingView.setVisibility(View.GONE);
                        binding.orderList.setVisibility(View.GONE);
                        binding.errorText.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewModel.getOrderAcceptedState().observe(getViewLifecycleOwner(), stringOrderState -> {
            if(stringOrderState != null) {
                switch (stringOrderState.status) {
                    case LOADING: {
                        binding.loadingView.setVisibility(View.VISIBLE);
                        break;
                    }
                    case SUCCESS: {
                        binding.loadingView.setVisibility(View.GONE);
                        viewModel.getAcceptedOrders();
                    }
                    case ERROR: {
                        Log.d(TAG, "Error: " + stringOrderState.throwable.getMessage());
                        binding.loadingView.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}