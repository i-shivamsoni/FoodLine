package com.example.foodline.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.repository.FoodRepository;
import com.example.foodline.utils.ScreenUtils;

import java.net.InetAddress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>();

    public LoginViewModel(Application application){
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }
    
    public void authenticateUser(){
        Call<DefaultResponse> call = foodRepository.authenticateUser(email.getValue(), password.getValue());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if(response.code() == 200) {
                    Toast.makeText(getApplication(), "Login Successful :)", Toast.LENGTH_SHORT).show();
                    DefaultResponse defaultResponse = response.body();
                    isAuthenticated.setValue(true);
                }else{
                    Toast.makeText(getApplication(), "Please, check your email and password and try again!!", Toast.LENGTH_SHORT).show();
                    isAuthenticated.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                if(!ScreenUtils.isInternetAvailable()){
                    Toast.makeText(getApplication(), "Check your internet connection and try again :(", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplication(), "Something went wrong :(", Toast.LENGTH_SHORT).show();
                }
                isAuthenticated.setValue(false);
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Boolean> getIsAuthenticated() {
        return isAuthenticated;
    }


}
