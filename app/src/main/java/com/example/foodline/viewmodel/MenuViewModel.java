package com.example.foodline.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.MenuItem;
import com.example.foodline.repository.FoodRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<List<MenuItem>> menuItems = new MutableLiveData<>();
    private MutableLiveData<List<MenuItem>> searchedMenuItems = new MutableLiveData<>();

    public MenuViewModel(Application application){
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
        menuItems.setValue(foodRepository.getMenuItems());
    }

    public MutableLiveData<List<MenuItem>> getMenuItems() {
        return menuItems;
    }

    public MutableLiveData<List<MenuItem>> getSearchedMenuItems() {
        return searchedMenuItems;
    }

    public void update(MenuItem menuItem) {
        foodRepository.updateMenuItem(menuItem);
    }
}
