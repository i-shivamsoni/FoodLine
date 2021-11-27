package com.example.foodline.network.order;

import com.google.gson.annotations.SerializedName;

public class OrderItemNetworkEntity {

	@SerializedName("orderItemid")
	private int orderItemid;

	@SerializedName("name")
	private String name;

	@SerializedName("qty")
	private int qty;

	@SerializedName("price")
	private String price;

	@SerializedName("image")
	private String image;

	public OrderItemNetworkEntity() {
	}

	public OrderItemNetworkEntity(String image, int orderItemid, String price, int qty, String name) {
		this.image = image;
		this.orderItemid = orderItemid;
		this.price = price;
		this.qty = qty;
		this.name = name;
	}

	public String getImage(){
		return image;
	}

	public int getOrderItemid(){
		return orderItemid;
	}

	public String getPrice(){
		return price;
	}

	public int getQty(){
		return qty;
	}

	public String getName(){
		return name;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setOrderItemid(int orderItemid) {
		this.orderItemid = orderItemid;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrderItemNetworkEntity{" +
				"image='" + image + '\'' +
				", orderItemid=" + orderItemid +
				", price='" + price + '\'' +
				", qty=" + qty +
				", name='" + name + '\'' +
				'}';
	}
}
