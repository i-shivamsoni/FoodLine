package com.example.foodline.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.repository.FoodRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Boolean> isAuthenticated = new MutableLiveData<>();

    public LoginViewModel(){
        this.foodRepository = new FoodRepository();
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
                    DefaultResponse defaultResponse = response.body();
                    isAuthenticated.setValue(true);
                }else{
                    isAuthenticated.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                isAuthenticated.setValue(false);
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<Boolean> getIsAuthenticated() {
        return isAuthenticated;
    }
}
