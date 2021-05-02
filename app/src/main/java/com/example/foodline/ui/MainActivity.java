package com.example.foodline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.foodline.R;
import com.example.foodline.databinding.ActivityMainBinding;
import com.example.foodline.utils.SharedPreferenceUtil;

import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_login);


    }

}