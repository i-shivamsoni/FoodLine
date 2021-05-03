package com.example.foodline.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodline.databinding.FragmentRegisterBinding;
import com.example.foodline.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel registerViewModel;

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

        registerViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(RegisterViewModel.class);

        setListeners();
        observeData();
    }

    private void observeData() {
        registerViewModel.getIsRegistered().observe(getViewLifecycleOwner(), isRegistered -> {
            if(isRegistered != null){
                if(isRegistered){
                    navigateToLogin();
                }

                binding.loadingView.setVisibility(View.GONE);
                requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                registerViewModel.getIsRegistered().setValue(null);
            }
        });
    }

    private void setListeners() {
        binding.registerBtn.setOnClickListener(v ->{
            registerUser();
        });

        binding.loginBtn.setOnClickListener(v -> navigateToLogin());

        binding.confirmPassText.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE){
                registerUser();
            }
            return false;
        });
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
    }

    private boolean isValidated() {
        boolean isEmailValid, isPasswordValid;

        if (binding.emailText.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Email is required!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailText.getText().toString()).matches()) {
            Toast.makeText(requireContext(), "Invalid Email!!", Toast.LENGTH_SHORT).show();
            return false;
        } else  {
            isEmailValid = true;
        }

        if (binding.passText.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Password is required!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.passText.getText().length() < 6) {
            Toast.makeText(requireContext(), "Password should be more than 6 letters!!", Toast.LENGTH_SHORT).show();
            return false;
        } else  {
            if(binding.passText.getText().toString().equals(binding.confirmPassText.getText().toString())){
                isPasswordValid = true;
            }else{
                Toast.makeText(requireContext(),"Passwords do not match!!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return isEmailValid && isPasswordValid;
    }
}