package com.example.foodline.ui.admin.admin_account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentAdminAccountBinding;
import com.example.foodline.repository.FoodRepository;
import com.example.foodline.ui.auth.AuthActivity;
import com.example.foodline.utils.SharedPreferenceUtil;

public class AdminAccountFragment extends Fragment {

    private FragmentAdminAccountBinding binding;
    private SharedPreferenceUtil sharedPreferenceUtil;

    public AdminAccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminAccountBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(getContext());
        setListeners();
    }

    private void setListeners() {
        binding.logoutBtn.setOnClickListener(v -> logout());
    }

    private void logout() {
        sharedPreferenceUtil.setIsAdmin(false);
        sharedPreferenceUtil.setIsLogin(false);

        navToAuth();
    }

    private void navToAuth() {
        FoodRepository.getInstance(getActivity().getApplication()).deleteUser();

        Intent intent = new Intent(getActivity(), AuthActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        getActivity().finish();
    }
}