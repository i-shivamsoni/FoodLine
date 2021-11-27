package com.example.foodline.network.order;

import java.util.List;

import com.example.foodline.network.user.UserNetworkEntity;
import com.google.gson.annotations.SerializedName;

public class OrderNetworkEntity{

	@SerializedName("isPaid")
	private boolean isPaid;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("totalPrice")
	private String totalPrice;

	@SerializedName("dish")
	private Object dish;

	@SerializedName("paymentMethod")
	private String paymentMethod;

	@SerializedName("taxPrice")
	private String taxPrice;

	@SerializedName("paidAt")
	private Object paidAt;

	@SerializedName("orderStatus")
	private Object orderStatus;

	@SerializedName("_id")
	private int id;

	@SerializedName("orderItems")
	private List<OrderItemNetworkEntity> orderItems;

	@SerializedName("user")
	private UserNetworkEntity user;

	public OrderNetworkEntity() {
	}

	public OrderNetworkEntity(boolean isPaid, String createdAt, String totalPrice, Object dish, String paymentMethod, String taxPrice, Object paidAt, Object orderStatus, int id, List<OrderItemNetworkEntity> orderItems, UserNetworkEntity user) {
		this.isPaid = isPaid;
		this.createdAt = createdAt;
		this.totalPrice = totalPrice;
		this.dish = dish;
		this.paymentMethod = paymentMethod;
		this.taxPrice = taxPrice;
		this.paidAt = paidAt;
		this.orderStatus = orderStatus;
		this.id = id;
		this.orderItems = orderItems;
		this.user = user;
	}

	public void setIsPaid(boolean isPaid){
		this.isPaid = isPaid;
	}

	public boolean isIsPaid(){
		return isPaid;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setTotalPrice(String totalPrice){
		this.totalPrice = totalPrice;
	}

	public String getTotalPrice(){
		return totalPrice;
	}

	public void setDish(Object dish){
		this.dish = dish;
	}

	public Object getDish(){
		return dish;
	}

	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod(){
		return paymentMethod;
	}

	public void setTaxPrice(String taxPrice){
		this.taxPrice = taxPrice;
	}

	public String getTaxPrice(){
		return taxPrice;
	}

	public void setPaidAt(Object paidAt){
		this.paidAt = paidAt;
	}

	public Object getPaidAt(){
		return paidAt;
	}

	public void setOrderStatus(Object orderStatus){
		this.orderStatus = orderStatus;
	}

	public Object getOrderStatus(){
		return orderStatus;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOrderItems(List<OrderItemNetworkEntity> orderItems){
		this.orderItems = orderItems;
	}

	public List<OrderItemNetworkEntity> getOrderItems(){
		return orderItems;
	}

	public void setUser(UserNetworkEntity user){
		this.user = user;
	}

	public UserNetworkEntity getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"OrderNetworkEntity{" + 
			"isPaid = '" + isPaid + '\'' + 
			",createdAt = '" + createdAt + '\'' + 
			",totalPrice = '" + totalPrice + '\'' + 
			",dish = '" + dish + '\'' + 
			",paymentMethod = '" + paymentMethod + '\'' + 
			",taxPrice = '" + taxPrice + '\'' + 
			",paidAt = '" + paidAt + '\'' + 
			",orderStatus = '" + orderStatus + '\'' + 
			",_id = '" + id + '\'' + 
			",orderItems = '" + orderItems + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}