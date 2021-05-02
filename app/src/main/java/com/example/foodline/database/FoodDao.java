package com.example.foodline.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodline.model.MenuItem;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert
    void insertAll(MenuItem... menuItems);

    @Update
    void update(MenuItem menuItem);

    @Query("SELECT * FROM menu_item_table WHERE counterInCart > 0")
    LiveData<List<MenuItem>> getCartMenuItem();

    @Query("DELETE FROM menu_item_table")
    void deleteAllMenuItems();
}
