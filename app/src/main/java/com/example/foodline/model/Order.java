package com.example.foodline.model;

import java.util.List;

public class Order {
    private int orderId;
    private List<OrderItem> orderItems;
    private User user;
    private String paymentMethod;
    private boolean isAccepted;
    private String taxPrice;
    private String totalPrice;

    public Order() {
    }

    public Order(int orderId, List<OrderItem> orderItems, User user, String paymentMethod, boolean isAccepted, String taxPrice, String totalPrice) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.user = user;
        this.paymentMethod = paymentMethod;
        this.isAccepted = isAccepted;
        this.taxPrice = taxPrice;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
