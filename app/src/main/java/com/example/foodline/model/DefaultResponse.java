package com.example.foodline.model;

public class DefaultResponse {
    private String username;
    private String email;
    private String name;
    private String token;

    public DefaultResponse(String username, String email, String name, String token) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
