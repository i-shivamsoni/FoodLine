package com.example.foodline.model;

public class MenuItem {
    private int id;
    private String name;
    private String category;
    private String imageUrl;
    private String rating;
    private String price;
    private int counterInStock;
    private int counterInCart;

    public MenuItem(String name, String category, String price, String imageUrl) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.counterInCart = 0;
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
