package com.example.foodline.network;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.model.MenuItem;
import com.example.foodline.network.menu.MenuNetworkEntity;
import com.example.foodline.network.user.UserNetworkEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FoodApi {

    @FormUrlEncoded
    @POST("/api/users/registeruser/")
    Observable<UserNetworkEntity> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/users/login/")
    Observable<UserNetworkEntity> login(
            @Field("username") String email,
            @Field("password") String password
    );

    @GET("/api/menu")
    Observable<List<MenuNetworkEntity>> getMenu();
}
