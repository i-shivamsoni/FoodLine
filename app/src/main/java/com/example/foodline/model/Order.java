package com.example.foodline.model;

import java.util.List;

public class Order {

    private List<OrderItem> orderItems;
    private String paymentMethod;
    private float taxPrice;
    private float totalPrice;

    public Order() {
    }

    public Order(List<OrderItem> orderItems, String paymentMethod, float taxPrice, float totalPrice) {
        this.orderItems = orderItems;
        this.paymentMethod = paymentMethod;
        this.taxPrice = taxPrice;
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(float taxPrice) {
        this.taxPrice = taxPrice;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderItems=" + orderItems.toString() +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", taxPrice=" + taxPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
