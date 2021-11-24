package com.example.foodline.ui.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodline.R;
import com.example.foodline.databinding.ActivityAdminBinding;
import com.example.foodline.utils.SharedPreferenceUtil;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);
        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this);

//        setListeners();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_admin);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
    }

//    private void setListeners() {
//        binding.logoutBtn.setOnClickListener(v -> logout());
//    }
//
//    private void logout() {
//        sharedPreferenceUtil.setIsAdmin(false);
//        sharedPreferenceUtil.setIsLogin(false);
//
//        navToAuth();
//    }
//
//    private void navToAuth() {
//        FoodRepository.getInstance(getApplication()).deleteUser();
//
//        Intent intent = new Intent(this, AuthActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        finish();
//    }
}
