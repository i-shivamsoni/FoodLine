package com.example.foodline.network.menu;

import com.google.gson.annotations.SerializedName;

public class MenuNetworkEntity {

	@SerializedName("menuid")
	private int id;

	@SerializedName("dish_name")
	private String dishName;

	@SerializedName("price")
	private String price;

	@SerializedName("rating")
	private String rating;

	@SerializedName("category")
	private String category;

	@SerializedName("image")
	private String image;

	@SerializedName("description")
	private String description;

	@SerializedName("counterInStock")
	private int counterInStock;

	public MenuNetworkEntity(int id, String dishName, String price, String rating, String category, String image, String description, int counterInStock) {
		this.id = id;
		this.dishName = dishName;
		this.price = price;
		this.rating = rating;
		this.category = category;
		this.image = image;
		this.description = description;
		this.counterInStock = counterInStock;
	}

	public MenuNetworkEntity(){
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setDishName(String dishName){
		this.dishName = dishName;
	}

	public String getDishName(){
		return dishName;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setCounterInStock(int counterInStock){
		this.counterInStock = counterInStock;
	}

	public int getCounterInStock(){
		return counterInStock;
	}

	@Override
	public String toString() {
		return "DishNetworkEntity{" +
				"id=" + id +
				", dishName='" + dishName + '\'' +
				", price='" + price + '\'' +
				", rating='" + rating + '\'' +
				", category='" + category + '\'' +
				", image='" + image + '\'' +
				", description='" + description + '\'' +
				", counterInStock=" + counterInStock +
				'}';
	}
}