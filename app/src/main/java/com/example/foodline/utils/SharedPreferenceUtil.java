package com.example.foodline.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class SharedPreferenceUtil {

    private static final String LOGIN_KEY = "login_key";
    private static final String USER_KEY = "user_key";

    private static SharedPreferenceUtil INSTANCE;
    private SharedPreferences sharedPreferences;

    private SharedPreferenceUtil(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceUtil getInstance(Context context){
        synchronized (SharedPreferenceUtil.class) {
            if(INSTANCE == null){
                INSTANCE = new SharedPreferenceUtil(context);
            }
        }
        return INSTANCE;
    }

    public boolean getIsLogin(){
        return sharedPreferences.getBoolean(LOGIN_KEY, false);
    }

    public void setIsLogin(boolean isLogin){
        sharedPreferences.edit().putBoolean(LOGIN_KEY, isLogin).apply();
    }

    public String getUserDetails(){
        return sharedPreferences.getString(USER_KEY, null);
    }

    public void setUserDetails(String user){
        sharedPreferences.edit().putString(USER_KEY, user).apply();
    }
}
