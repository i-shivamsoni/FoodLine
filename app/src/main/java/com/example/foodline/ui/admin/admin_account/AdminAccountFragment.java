package com.example.foodline.ui.admin.admin_account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodline.databinding.FragmentAdminAccountBinding;

public class AdminAccountFragment extends Fragment {

    private FragmentAdminAccountBinding binding;

    public AdminAccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminAccountBinding.inflate(inflater);
        return binding.getRoot();
    }
}