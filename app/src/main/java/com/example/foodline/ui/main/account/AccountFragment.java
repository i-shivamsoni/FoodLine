package com.example.foodline.ui.main.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentAccountBinding;
import com.example.foodline.model.DefaultResponse;
import com.example.foodline.ui.auth.AuthActivity;
import com.example.foodline.ui.main.MainActivity;
import com.example.foodline.utils.SharedPreferenceUtil;
import com.google.gson.Gson;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    private AccountViewModel accountViewModel;

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

        accountViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(AccountViewModel.class);

        binding.logoutBtn.setOnClickListener(v -> logout());

        observeData();
    }

    private void logout() {
        accountViewModel.setIsLogin(false);
        Intent i = new Intent(requireActivity(), AuthActivity.class);
        startActivity(i);
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        requireActivity().finish();
    }

    private void observeData() {

        accountViewModel.getUserData().observe(getViewLifecycleOwner(), user -> {
            if(user != null){

                binding.userName.setText(user.getName());
            }
        });
    }
}