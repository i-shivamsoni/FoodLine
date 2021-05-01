package com.example.foodline.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.foodline.R;
import com.example.foodline.databinding.ActivityBaseBinding;

import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem;

public class BaseActivity extends AppCompatActivity {

    private ActivityBaseBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_order);

        CbnMenuItem[] cbnMenuItems = new CbnMenuItem[3];

        cbnMenuItems[0] = new CbnMenuItem(R.drawable.ic_menu, R.drawable.ic_menu_avd, R.id.menuFragment);
        cbnMenuItems[1] = new CbnMenuItem(R.drawable.ic_cart, R.drawable.ic_cart_avd, R.id.cartFragment);
        cbnMenuItems[2] = new CbnMenuItem(R.drawable.ic_account, R.drawable.ic_account_avd, R.id.accountFragment);


        binding.bottomNav.setMenuItems(cbnMenuItems, 0);
        binding.bottomNav.setupWithNavController(navController);

        binding.filterIcon.setOnClickListener(v -> {
            showFilterAlertDialog();
        });
    }

    private void showFilterAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.MaterialThemeDialog);

        String items[] = {"None", "Bevarages", "Chinese", "Desert"};
        int checkedItem = 0;
        alertDialog.setTitle("Filter");
        alertDialog.setSingleChoiceItems(items, checkedItem, (dialog, which) -> {
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void setAppBar(int fragment) {
        if(fragment == 0){
            binding.screenIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_menu));
            binding.screenTitle.setText("Menu");
            binding.filterIcon.setVisibility(View.VISIBLE);
        }else if(fragment == 1){
            binding.screenIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_cart));
            binding.screenTitle.setText("Cart");
            binding.filterIcon.setVisibility(View.INVISIBLE);
        }else if(fragment == 2){
            binding.screenIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_account));
            binding.screenTitle.setText("Account");
            binding.filterIcon.setVisibility(View.INVISIBLE);
        }
    }
}