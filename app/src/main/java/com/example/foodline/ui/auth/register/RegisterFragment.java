package com.example.foodline.ui.auth.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.foodline.databinding.FragmentRegisterBinding;

import retrofit2.HttpException;

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
        registerViewModel.getRegisterState().observe(getViewLifecycleOwner(), userRegisterState -> {
            if(userRegisterState != null){

                switch (userRegisterState.status){

                    case LOADING:{
                        showProgressBar(true);
                        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        break;
                    }

                    case REGISTERED:{
                        showProgressBar(false);
                        showToast("Registered successfully :)");
                        navigateToLogin();
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        break;
                    }

                    case ERROR:{
                        showProgressBar(false);
                        try {
                            if(((HttpException)userRegisterState.throwable).code() == 400){
                                showToast("You are already registered :p");
                            }
                        }catch (Exception e){
                            showToast("Check your Internet connection and try again :(");
                        }
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        break;
                    }
                }
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

    private void showProgressBar(boolean isDisplayed){
        if(isDisplayed){
            binding.loadingView.setVisibility(View.VISIBLE);
        }else{
            binding.loadingView.setVisibility(View.GONE);
        }
    }

    private void navigateToLogin(){
        NavHostFragment.findNavController(this).navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment());
    }

    private void registerUser() {
        if(!isValidated())
            return;

        binding.loadingView.setVisibility(View.VISIBLE);
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        registerViewModel.registerUser(binding.nameText.getText().toString().trim(), binding.emailText.getText().toString().trim(),
                binding.passText.getText().toString().trim());
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

    private void showToast(String text){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show();
    }
}