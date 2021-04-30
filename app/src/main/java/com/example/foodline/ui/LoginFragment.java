package com.example.foodline.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private NavController navController;
    private boolean isAuthenticated = true;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = NavHostFragment.findNavController(this);

        binding.loginBtn.setOnClickListener(v -> {
            authenticateUser();
        });

        binding.registerBtn.setOnClickListener(v -> {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
        });
    }

    private void authenticateUser() {
        // Write authentication code and store the boolean value in isAuthenticated
        // Use binding.emailText for email and use binding.passText for pass
        // Use binding.emailText.getText.toString to get string stored in that view and similarly for other :)

        if(isAuthenticated){
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToMenuFragment());
        }
    }
}