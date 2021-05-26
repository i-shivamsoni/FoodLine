package com.example.foodline.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodline.R;
import com.example.foodline.databinding.ActivityMainBinding;
import com.example.foodline.repository.FoodRepository;

import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private FoodRepository foodRepository;
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        foodRepository = FoodRepository.getInstance(getApplication());

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_order);

        CbnMenuItem[] cbnMenuItems = new CbnMenuItem[3];

        cbnMenuItems[0] = new CbnMenuItem(R.drawable.ic_menu, R.drawable.ic_menu_avd, R.id.menuFragment);
        cbnMenuItems[1] = new CbnMenuItem(R.drawable.ic_cart, R.drawable.ic_cart_avd, R.id.cartFragment);
        cbnMenuItems[2] = new CbnMenuItem(R.drawable.ic_account, R.drawable.ic_account_avd, R.id.accountFragment);

        binding.bottomNav.setMenuItems(cbnMenuItems, 0);
        binding.bottomNav.setupWithNavController(navController);

        setListeners();
    }

    public void setListeners(){
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(destination.getLabel().toString().toLowerCase().equals("searchfragment")){
                binding.bottomNav.setVisibility(View.GONE);
            }else{
                binding.bottomNav.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        foodRepository.deleteMenu();
    }
}