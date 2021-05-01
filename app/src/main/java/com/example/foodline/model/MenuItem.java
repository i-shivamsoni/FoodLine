package com.example.foodline.model;

public class MenuItem {
    private String name;
    private String category;
    private String price;
    private String imageUrl;
    private int inCart;

    public MenuItem(String name, String category, String price, String imageUrl, int inCart) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.inCart = inCart;
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

    public int getInCart() {
        return inCart;
    }

    public void setInCart(int inCart) {
        this.inCart = inCart;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", inCart=" + inCart +
                '}';
    }
}
