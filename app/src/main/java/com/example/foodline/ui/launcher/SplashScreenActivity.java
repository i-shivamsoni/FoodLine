package com.example.foodline.ui.launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.foodline.R;
import com.example.foodline.ui.admin.AdminActivity;
import com.example.foodline.ui.auth.AuthActivity;
import com.example.foodline.ui.main.MainActivity;
import com.example.foodline.utils.SharedPreferenceUtil;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this);

        new Handler().postDelayed(() -> {
            Intent i;

            if(sharedPreferenceUtil.getIsLogin()){
                if(sharedPreferenceUtil.getIsAdmin()) {
                    i = new Intent(this, AdminActivity.class);
                } else {
                    i = new Intent(this, MainActivity.class);
                }
            }else {
                i = new Intent(this, AuthActivity.class);
            }

            changeActivity(i);
        },800);
    }

    private void changeActivity(Intent i) {
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}