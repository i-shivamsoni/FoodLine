package com.example.foodline.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodline.R;
import com.example.foodline.databinding.FragmentLoginBinding;
import com.example.foodline.utils.ScreenUtils;
import com.example.foodline.utils.SharedPreferenceUtil;
import com.example.foodline.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private NavController navController;

    private LoginViewModel loginViewModel;
    private SharedPreferenceUtil sharedPreferenceUtil;

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
        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(requireContext());

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
                ScreenUtils.hideKeyboard(requireActivity());
                authenticateUser();
                return true;
            }
            return false;
        });
    }

    private void observeData() {
        loginViewModel.getIsAuthenticated().observe(getViewLifecycleOwner(), check -> {
            if(check!=null){
                if(check){
                    sharedPreferenceUtil.setIsLogin(true);
                    Intent i = new Intent(requireActivity(), BaseActivity.class);
                    startActivity(i);
                    requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    requireActivity().finish();
                }

                binding.loadingView.setVisibility(View.GONE);
                requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                loginViewModel.getIsAuthenticated().setValue(null);
            }
        });
    }

    private void authenticateUser() {
        if(!isValidated())
            return;

        binding.loadingView.setVisibility(View.VISIBLE);
        requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        loginViewModel.getEmail().setValue(binding.emailText.getText().toString().trim());
        loginViewModel.getPassword().setValue(binding.passText.getText().toString().trim());

        loginViewModel.authenticateUser();
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


}