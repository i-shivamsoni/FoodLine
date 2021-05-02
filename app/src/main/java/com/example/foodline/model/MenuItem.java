package com.example.foodline.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu_item_table")
public class MenuItem {

    @PrimaryKey
    private int id;
    private String name;
    private String category;
    private String imageUrl;
    private String rating;
    private String price;
    private int counterInStock;
    private int counterInCart;

    public MenuItem(int id,String name, String category, String price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.counterInCart = 0;
    }

    @Ignore
    public MenuItem(int id, String name, String category, String imageUrl, String rating, String price, int counterInStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.price = price;
        this.counterInStock = counterInStock;
        this.counterInCart = 0;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getCounterInStock() {
        return counterInStock;
    }

    public void setCounterInStock(int counterInStock) {
        this.counterInStock = counterInStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCounterInCart() {
        return counterInCart;
    }

    public void setCounterInCart(int counterInCart) {
        this.counterInCart = counterInCart;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", inCart=" + counterInCart +
                '}';
    }
}
