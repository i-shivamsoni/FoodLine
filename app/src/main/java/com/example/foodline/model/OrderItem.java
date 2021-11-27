package com.example.foodline.model;

public class OrderItem {
    private int orderItemId;
    private String orderName;
    private int quantity;
    private String price;
    private String image;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, String orderName, int quantity, String price, String image) {
        this.orderItemId = orderItemId;
        this.orderName = orderName;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
