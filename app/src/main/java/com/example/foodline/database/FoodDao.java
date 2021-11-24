package com.example.foodline.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodline.database.menu.MenuCacheEntity;
import com.example.foodline.database.user.UserCacheEntity;
import com.example.foodline.model.MenuItem;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(MenuCacheEntity menuItem);

    @Update
    void update(MenuCacheEntity menuItem);

    @Query("SELECT * FROM menu_table")
    List<MenuCacheEntity> getMenu();

    @Query("SELECT * FROM menu_table WHERE counter_in_cart > 0")
    List<MenuCacheEntity> getCartMenuItem();

    @Query("DELETE FROM menu_table")
    void deleteAllMenuItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(UserCacheEntity user);

    @Query("SELECT * FROM user_table")
    UserCacheEntity getUser();

    @Query("DELETE FROM user_table")
    void deleteUser();
}
