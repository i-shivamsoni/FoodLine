package com.example.foodline.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentRegisterBinding;
import com.example.foodline.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;
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

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        setListeners();
        observeData();
    }

    private void observeData() {
        registerViewModel.getIsRegistered().observe(getViewLifecycleOwner(), isRegistered -> {
            if(isRegistered != null){
                binding.loadingView.setVisibility(View.GONE);
                requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                registerViewModel.getIsRegistered().setValue(null);

                if(isRegistered){
                    Toast.makeText(requireContext(), "Registered Successfully :)", Toast.LENGTH_SHORT).show();
                    navigateToLogin();
                }else{
                    Toast.makeText(requireContext(), "Something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setListeners() {
        binding.registerBtn.setOnClickListener(v ->{
            registerUser();
        });

        binding.loginBtn.setOnClickListener(v -> navigateToLogin());
    }

    private void navigateToLogin(){
        NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment());
    }

    private void registerUser() {
        if(!isValidated())
            return;

        binding.loadingView.setVisibility(View.VISIBLE);
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        registerViewModel.getName().setValue(binding.nameText.getText().toString());
        registerViewModel.getEmail().setValue(binding.emailText.getText().toString());
        registerViewModel.getPassword().setValue(binding.passText.getText().toString());

        registerViewModel.registerUser();

        if(isRegisterSuccessful){
            Toast.makeText(getContext(), "Registered :)", Toast.LENGTH_SHORT).show();
            navigateToLogin();
        }
    }

    private boolean isValidated() {
        boolean isEmailValid, isPasswordValid;

        if (binding.emailText.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Invalid Email!!", Toast.LENGTH_SHORT).show();
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailText.getText().toString()).matches()) {
            Toast.makeText(requireContext(), "Invalid Email!!", Toast.LENGTH_SHORT).show();
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        if (binding.passText.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Invalid Password!!", Toast.LENGTH_SHORT).show();
            isPasswordValid = false;
        } else if (binding.passText.getText().length() < 6) {
            Toast.makeText(requireContext(), "Invalid Password!!", Toast.LENGTH_SHORT).show();
            isPasswordValid = false;
        } else  {
            if(binding.passText.getText().toString().equals(binding.confirmPassText.getText().toString())){
                isPasswordValid = true;
            }else{
                isPasswordValid = false;
                Toast.makeText(requireContext(), "Passwords do not match!!", Toast.LENGTH_SHORT).show();
            }
        }

        return isEmailValid && isPasswordValid;
    }
}