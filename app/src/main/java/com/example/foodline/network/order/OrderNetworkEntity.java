package com.example.foodline.network.order;

import java.util.List;

import com.example.foodline.network.user.UserNetworkEntity;
import com.google.gson.annotations.SerializedName;

public class OrderNetworkEntity{

	@SerializedName("orderid")
	private int orderid;

	@SerializedName("orderItems")
	private List<OrderItemNetworkEntity> orderItems;

	@SerializedName("user")
	private User user;

	@SerializedName("paymentMethod")
	private String paymentMethod;

	@SerializedName("isAccepted")
	private boolean isAccepted;

	@SerializedName("taxPrice")
	private String taxPrice;

	@SerializedName("totalPrice")
	private String totalPrice;

	@SerializedName("createdAt")
	private String createdAt;

	public OrderNetworkEntity() {
	}

	public OrderNetworkEntity(String createdAt, int orderid, String totalPrice, boolean isAccepted, String paymentMethod, String taxPrice, List<OrderItemNetworkEntity> orderItems, User user) {
		this.createdAt = createdAt;
		this.orderid = orderid;
		this.totalPrice = totalPrice;
		this.isAccepted = isAccepted;
		this.paymentMethod = paymentMethod;
		this.taxPrice = taxPrice;
		this.orderItems = orderItems;
		this.user = user;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getOrderid(){
		return orderid;
	}

	public String getTotalPrice(){
		return totalPrice;
	}

	public boolean isIsAccepted(){
		return isAccepted;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public String getTaxPrice(){
		return taxPrice;
	}

	public List<OrderItemNetworkEntity> getOrderItems(){
		return orderItems;
	}

	public User getUser(){
		return user;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setAccepted(boolean accepted) {
		isAccepted = accepted;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}

	public void setOrderItems(List<OrderItemNetworkEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	@Override
	public String toString() {
		return "OrderNetworkEntity{" +
				"createdAt='" + createdAt + '\'' +
				", orderid=" + orderid +
				", totalPrice='" + totalPrice + '\'' +
				", isAccepted=" + isAccepted +
				", paymentMethod='" + paymentMethod + '\'' +
				", taxPrice='" + taxPrice + '\'' +
				", orderItems=" + orderItems +
				", user=" + user +
				'}';
	}
}