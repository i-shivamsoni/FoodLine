package com.example.foodline.network.order;

import com.google.gson.annotations.SerializedName;

public class OrderItemNetworkEntity {

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private String price;

	@SerializedName("qty")
	private int qty;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private int id;

	@SerializedName("menu")
	private int menu;

	@SerializedName("order")
	private int order;

	public OrderItemNetworkEntity() {
	}

	public OrderItemNetworkEntity(String image, String price, int qty, String name, int id, int menu, int order) {
		this.image = image;
		this.price = price;
		this.qty = qty;
		this.name = name;
		this.id = id;
		this.menu = menu;
		this.order = order;
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

	public void setQty(int qty){
		this.qty = qty;
	}

	public int getQty(){
		return qty;
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

	public void setMenu(int menu){
		this.menu = menu;
	}

	public int getMenu(){
		return menu;
	}

	public void setOrder(int order){
		this.order = order;
	}

	public int getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"OrderItemsItem{" + 
			"image = '" + image + '\'' + 
			",price = '" + price + '\'' + 
			",qty = '" + qty + '\'' + 
			",name = '" + name + '\'' + 
			",_id = '" + id + '\'' + 
			",menu = '" + menu + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}