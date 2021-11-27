package com.example.foodline.database.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class UserCacheEntity{

	@PrimaryKey(autoGenerate = false)
	@ColumnInfo(name = "id")
	private int id;

	@ColumnInfo(name = "name")
	private String name;

	@ColumnInfo(name = "username")
	private String username;

	@ColumnInfo(name = "email")
	private String email;

	@ColumnInfo(name = "is_admin")
	private boolean isAdmin;

	@ColumnInfo(name = "token")
	private String token;

	public UserCacheEntity(int id, String name, String username, String email, boolean isAdmin, String token) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.isAdmin = isAdmin;
		this.token = token;
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

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	@Override
	public String toString() {
		return "UserCacheEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", isAdmin=" + isAdmin +
				", token='" + token + '\'' +
				'}';
	}
}
