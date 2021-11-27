package com.example.foodline.model;

public class MenuItem {
	private int id;
	private String name;
	private String price;
	private String rating;
	private String category;
	private String imageUrl;
	private String description;
	private int counterInStock;
	private int counterInCart;

	public MenuItem(int id, String name, String price, String rating, String category, String imageUrl, String description, int counterInStock, int counterInCart) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.category = category;
		this.imageUrl = imageUrl;
		this.description = description;
		this.counterInStock = counterInStock;
		this.counterInCart = counterInCart;
	}

	public MenuItem(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCounterInCart(int counterInCart){
		this.counterInCart = counterInCart;
	}

	public int getCounterInCart(){
		return counterInCart;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Dish{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price='" + price + '\'' +
				", rating='" + rating + '\'' +
				", category='" + category + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", description='" + description + '\'' +
				", counterInStock=" + counterInStock +
				", counterInCart=" + counterInCart +
				'}';
	}
}
