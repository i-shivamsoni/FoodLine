package com.example.foodline.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.MenuItem;
import com.example.foodline.repository.FoodRepository;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private LiveData<List<MenuItem>> cartItems;
    private MutableLiveData<List<MenuItem>> menuItems = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
        this.cartItems = foodRepository.getCartMenuItem();
        this.menuItems = foodRepository.getMenuItems();
    }

//    private void initializeMenuItems() {
//        foodRepository.getMenuItems().observe(getApplication(), menuList -> {
//            menuItems.setValue(menuList);
//        });
//    }

    public LiveData<List<MenuItem>> getCartItems() {
        return cartItems;
    }

    public MutableLiveData<List<MenuItem>> getMenuItems() {
        return menuItems;
    }

    public void update(MenuItem menuItem) {
        foodRepository.updateMenuItem(menuItem);
    }
}
