package com.example.foodline.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodline.model.MenuItem;

@Database(entities = {MenuItem.class}, version = 1, exportSchema = false)
public abstract class FoodDatabase extends RoomDatabase {

    private static FoodDatabase INSTANCE;

    public abstract FoodDao foodDao();

    public static FoodDatabase getInstance(Context context){
        synchronized (FoodDatabase.class){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FoodDatabase.class, "food_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
}
