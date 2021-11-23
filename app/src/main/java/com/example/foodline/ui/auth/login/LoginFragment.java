package com.example.foodline.ui.auth.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentLoginBinding;
import com.example.foodline.ui.admin.AdminActivity;
import com.example.foodline.ui.main.MainActivity;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private FragmentLoginBinding binding;
    private NavController navController;

    private LoginViewModel loginViewModel;

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
        loginViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(LoginViewModel.class);

        setListeners();
        observeData();
    }

    private void setListeners() {
        binding.loginBtn.setOnClickListener(v -> {
            authenticateUser();
        });

        binding.registerBtn.setOnClickListener(v -> {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
        });

        binding.passText.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE){
                v.clearFocus();
                authenticateUser();
                return true;
            }
            return false;
        });
    }

    private void authenticateUser() {
        if(!isValidated())
            return;

        loginViewModel.authenticateUser(binding.emailText.getText().toString().trim(),
                binding.passText.getText().toString().trim());
    }

    private void observeData() {
        loginViewModel.getLoginState().observe(getViewLifecycleOwner(), userLoginState -> {
            if(userLoginState != null){
                Log.d(TAG, "subscribeObservers: " + userLoginState.status);

                switch (userLoginState.status){

                    case LOADING:{
                        showProgressBar(true);
                        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        break;
                    }

                    case AUTHENTICATED:{
                        showProgressBar(false);
                        showToast("Login Successful :)");
                        loginViewModel.setIsLogin(true);


                        if (userLoginState.data != null && userLoginState.data.getIsAdmin()) {
                            loginViewModel.setIsAdmin(true);
                            navToAdmin();
                        } else {
                            navToMain();
                        }

                        Log.d(TAG, userLoginState.data.toString());

                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        break;
                    }

                    case ERROR:{
                        showProgressBar(false);
                        showToast("Check your Internet connection and try again :(");
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        break;
                    }

                    case NOT_AUTHENTICATED:{
                        showProgressBar(false);
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        break;
                    }
                }
            }
        });
    }

    private void navToAdmin() {
        Activity activity = requireActivity();

        Intent intent = new Intent(activity, AdminActivity.class);
        startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        activity.finish();
    }

    private void navToMain() {
        Activity activity = requireActivity();

        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        activity.finish();
    }

    private void showProgressBar(boolean isDisplayed) {
        if(isDisplayed)
            binding.loadingView.setVisibility(View.VISIBLE);
        else
            binding.loadingView.setVisibility(View.GONE);
    }

    private boolean isValidated() {
        boolean isEmailValid, isPasswordValid;

        if(binding.emailText.getText().toString().isEmpty() && binding.passText.getText().toString().isEmpty()){
            Toast.makeText(requireContext(), "Email and Password are required!!", Toast.LENGTH_SHORT).show();
            return false;
        }

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
            Toast.makeText(requireContext(), "Invalid Password!!", Toast.LENGTH_SHORT).show();
            return false;
        } else  {
            isPasswordValid = true;
        }

        return isEmailValid && isPasswordValid;
    }

    private void showToast(String text){
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show();
    }

}