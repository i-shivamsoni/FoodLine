package com.example.foodline.ui.admin.current_orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodline.databinding.FragmentCurrentOrdersBinding;

public class CurrentOrdersFragment extends Fragment {

    private FragmentCurrentOrdersBinding binding;

    public CurrentOrdersFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCurrentOrdersBinding.inflate(inflater);
        return binding.getRoot();
    }
}
