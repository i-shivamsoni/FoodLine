package com.example.foodline.network;

import com.example.foodline.network.menu.MenuNetworkEntity;
import com.example.foodline.network.user.UserNetworkEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodApiService {
    private static final String BASE_URL = "https://foodline-demo.herokuapp.com/";

    private final FoodApi api;
    private static FoodApiService INSTANCE;

    private FoodApiService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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

    public Observable<UserNetworkEntity> authenticateUser(String email, String password){
        return api.login(email, password);
    }

    public Observable<UserNetworkEntity> registerUser(String name, String email, String password){
        return api.register(name, email, password);
    }

    public Observable<List<MenuNetworkEntity>> getMenu(){
        return api.getMenu();
    }
}
