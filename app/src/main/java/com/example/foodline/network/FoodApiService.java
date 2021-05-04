package com.example.foodline.network;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.model.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodApiService {
    private static final String BASE_URL = "https://foodline-demo.herokuapp.com/";

    private FoodApi api;
    private static FoodApiService INSTANCE;

    private FoodApiService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(FoodApi.class);
    }

    public static FoodApiService getInstance(){
        synchronized (FoodApiService.class) {
            if (INSTANCE == null) {
                INSTANCE = new FoodApiService();
            }
        }
        return INSTANCE;
    }

    public Call<DefaultResponse> authenticateUser(String email, String password){
        return api.login(email, password);
    }

    public Call<DefaultResponse> registerUser(String name, String email, String password){
        return api.register(name, email, password);
    }

    public Call<List<MenuItem>> getMenu(){
        return api.getMenu();
    }
}
