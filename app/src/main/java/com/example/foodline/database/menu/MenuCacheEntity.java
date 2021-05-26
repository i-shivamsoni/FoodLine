package com.example.foodline.database.menu;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "menu_table")
public class MenuCacheEntity {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "counter_in_stock")
    private int counterInStock;

    @ColumnInfo(name = "counter_in_cart")
    private int counterInCart;

    public MenuCacheEntity(int id, String name, String price, String rating, String category, String imageUrl, String description, int counterInStock, int counterInCart) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.category = category;
        this.imageUrl = imageUrl;
        this.description = description;
        this.counterInStock = counterInStock;
        this.counterInCart = counterInCart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCounterInCart(int counterInCart){
        this.counterInCart = counterInCart;
    }

    public int getCounterInCart(){
        return counterInCart;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getPrice(){
        return price;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setRating(String rating){
        this.rating = rating;
    }

    public String getRating(){
        return rating;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public void setCounterInStock(int counterInStock){
        this.counterInStock = counterInStock;
    }

    public int getCounterInStock(){
        return counterInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", rating='" + rating + '\'' +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", counterInStock=" + counterInStock +
                ", counterInCart=" + counterInCart +
                '}';
    }
}
