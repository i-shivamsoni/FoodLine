package com.example.foodline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.foodline.R;
import com.example.foodline.utils.SharedPreferenceUtil;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(this);

        new Handler().postDelayed(() ->{
            if(sharedPreferenceUtil.getIsLogin()){
                Intent i = new Intent(SplashScreenActivity.this, BaseActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }else {

                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        },800);
    }
}