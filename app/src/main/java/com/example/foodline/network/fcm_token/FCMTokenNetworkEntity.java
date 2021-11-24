package com.example.foodline.network.fcm_token;


import com.google.gson.annotations.SerializedName;

public class FCMTokenNetworkEntity {

    @SerializedName("id")
    private int id;

    @SerializedName("token")
    private String token;

    @SerializedName("userid")
    private int userId;

    public FCMTokenNetworkEntity() {
    }

    public FCMTokenNetworkEntity(int id, String token, int userId) {
        this.id = id;
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FCMTokenNetworkEntity{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
