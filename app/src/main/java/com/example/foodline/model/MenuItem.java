package com.example.foodline.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "menu_item_table")
public class MenuItem {

    @SerializedName("_id")
    @PrimaryKey
    private int id;

    @SerializedName("dish_name")
    private String name;

    private String category;

    @SerializedName("image")
    private String imageUrl;

    private String rating;

    @SerializedName("numReviews")
    private int numOfReviews;

    private String price;

    private int counterInStock;

    private int counterInCart;

    public MenuItem(int id, String name, String category, String imageUrl, String rating, int numOfReviews, String price, int counterInStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.numOfReviews = numOfReviews;
        this.price = price;
        this.counterInStock = counterInStock;
        this.counterInCart = 0;
    }

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public void setNumOfReviews(int numOfReviews) {
        this.numOfReviews = numOfReviews;
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
