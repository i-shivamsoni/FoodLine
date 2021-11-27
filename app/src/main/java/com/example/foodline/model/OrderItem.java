package com.example.foodline.model;

public class OrderItem {
    private int dish;
    private int qty;
    private float price;

    public OrderItem() {
    }

    public OrderItem(int dishId, int qty, float price) {
        this.dish = dishId;
        this.qty = qty;
        this.price = price;
    }

    public int getDishId() {
        return dish;
    }

    public void setDishId(int dishId) {
        this.dish = dishId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "dishId=" + dish +
                ", quantity=" + qty +
                ", price=" + price +
                '}';
    }
}
