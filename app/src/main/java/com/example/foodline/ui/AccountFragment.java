package com.example.foodline.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentAccountBinding;
import com.example.foodline.model.DefaultResponse;
import com.example.foodline.utils.SharedPreferenceUtil;
import com.google.gson.Gson;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private SharedPreferenceUtil sharedPreferenceUtil;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) requireActivity()).setAppBar(2);

        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(requireContext());

        DefaultResponse defaultResponse = new Gson().fromJson(sharedPreferenceUtil.getUserDetails(), DefaultResponse.class);

        binding.userName.setText(defaultResponse.getName());

        binding.logoutBtn.setOnClickListener(v -> {
            sharedPreferenceUtil.setIsLogin(false);
            Intent i = new Intent(requireActivity(), MainActivity.class);
            startActivity(i);
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            requireActivity().finish();
        });
    }
}