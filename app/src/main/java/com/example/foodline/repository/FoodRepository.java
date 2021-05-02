package com.example.foodline.repository;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.network.FoodApiService;

import retrofit2.Call;

public class FoodRepository {

    private FoodApiService foodApiService;

    public FoodRepository(){
        this.foodApiService = FoodApiService.getInstance();
    }

    public Call<DefaultResponse> authenticateUser(String email, String password){
        return foodApiService.authenticateUser(email, password);
    }

    public Call<DefaultResponse> registerUser(String name, String email, String password){
        return foodApiService.registerUser(name, email, password);
    }

}
