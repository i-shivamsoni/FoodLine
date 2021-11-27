package com.example.foodline.model;

public class User {
	private int id;
	private String name;
	private String username;
	private String email;
	private boolean isAdmin;
	private String token;

	public User(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public User(int id, String name, String username, String email, boolean isAdmin, String token) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.isAdmin = isAdmin;
		this.token = token;
	}

	public User(){
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", isAdmin=" + isAdmin +
				", token='" + token + '\'' +
				'}';
	}
}
