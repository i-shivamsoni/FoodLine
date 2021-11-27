package com.example.foodline.network.user;

import com.example.foodline.model.User;
import com.google.gson.annotations.SerializedName;

public class UserNetworkEntity{

	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("username")
	private String username;

	@SerializedName("email")
	private String email;

	@SerializedName("isAdmin")
	private boolean isAdmin;

	@SerializedName("token")
	private String token;

	public UserNetworkEntity(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public UserNetworkEntity(int id, String name, String username, String email, boolean isAdmin, String token) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.isAdmin = isAdmin;
		this.token = token;
	}

	public UserNetworkEntity() {
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}

	public boolean getIsAdmin(){
		return isAdmin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
	public String toString() {
		return "UserNetworkEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", isAdmin=" + isAdmin +
				", token='" + token + '\'' +
				'}';
	}
}