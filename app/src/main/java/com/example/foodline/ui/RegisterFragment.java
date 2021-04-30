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
import android.widget.Toast;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private boolean isRegisterSuccessful = false;

    public RegisterFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerBtn.setOnClickListener(v ->{
            registerUser();
        });

        binding.loginBtn.setOnClickListener(v -> navigateToLogin());
    }

    private void navigateToLogin(){
        NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment());
    }

    private void registerUser() {
        // Write Registration code and if registration is successful store it in isRegisterSuccessful
        // Use binding.emailText for email, binding.nameText for name, binding.passText for pass, binding.confirmPassText for confirm pass
        // Use binding.emailText.getText.toString to get string stored in that view and similarly for other :)

        if(isRegisterSuccessful){
            Toast.makeText(getContext(), "Registered :)", Toast.LENGTH_SHORT).show();
            navigateToLogin();
        }
    }
}