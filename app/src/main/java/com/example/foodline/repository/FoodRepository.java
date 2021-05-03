package com.example.foodline.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.foodline.database.FoodDao;
import com.example.foodline.database.FoodDatabase;
import com.example.foodline.model.DefaultResponse;
import com.example.foodline.model.MenuItem;
import com.example.foodline.network.FoodApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class FoodRepository {

    private static FoodRepository INSTANCE;

    private FoodApiService foodApiService;
    private List<MenuItem> menuItems;
    private Application application;
    private FoodDao foodDao;

    private FoodRepository(Application application){
        this.foodApiService = FoodApiService.getInstance();
        this.menuItems = new ArrayList<MenuItem>();
        this.application = application;
        this.foodDao = FoodDatabase.getInstance(application).foodDao();

        initializeMenuItem();
    }

    public static FoodRepository getInstance(Application application){
        synchronized (FoodRepository.class){
            if(INSTANCE == null){
                INSTANCE = new FoodRepository(application);
            }
        }
        return INSTANCE;
    }

    private void initializeMenuItem() {
        menuItems.add(new MenuItem(1,"Pizza", "Fast Food", "200",""));
        menuItems.add(new MenuItem(2,"Burger", "Fast Food", "50",""));
        menuItems.add(new MenuItem(3,"Coffee", "Fast Food", "20",""));
        menuItems.add(new MenuItem(4,"Tea", "Fast Food", "20",""));
        menuItems.add(new MenuItem(5,"Samosa", "Fast Food", "20",""));
        menuItems.add(new MenuItem(6,"Hot Dog", "Fast Food", "90",""));
        menuItems.add(new MenuItem(7,"Thali", "Fast Food", "70",""));
        menuItems.add(new MenuItem(8,"Dhosa", "Fast Food", "70",""));
        menuItems.add(new MenuItem(9,"Pav Bhaji", "Fast Food", "60",""));
        menuItems.add(new MenuItem(10,"Sprite", "Fast Food", "30",""));
        menuItems.add(new MenuItem(11,"Frooti", "Fast Food", "30",""));
        menuItems.add(new MenuItem(12,"Maaza", "Fast Food", "30",""));
        menuItems.add(new MenuItem(13,"Pasta", "Fast Food", "40",""));
        menuItems.add(new MenuItem(14, "Maggi", "Fast Food", "40",""));
        menuItems.add(new MenuItem(15,"Chowmein", "Fast Food", "40",""));
        menuItems.add(new MenuItem(16,"Manchurian", "Fast Food", "40",""));
        menuItems.add(new MenuItem(17,"Paneer", "Fast Food", "40",""));
        menuItems.add(new MenuItem(18,"Chicken Tikka", "Fast Food", "80",""));
        menuItems.add(new MenuItem(19,"Veg Roll", "Fast Food", "50",""));

        InsertMenuItemsTask insertMenuItemsTask = new InsertMenuItemsTask(foodDao, application);
        insertMenuItemsTask.execute(menuItems);
    }

    public void updateMenuItem(MenuItem menuItem){
        UpdateMenuItemsTask updateMenuItemsTask = new UpdateMenuItemsTask(foodDao, application);
        updateMenuItemsTask.execute(menuItem);
    }

    public Call<DefaultResponse> authenticateUser(String email, String password){
        return foodApiService.authenticateUser(email, password);
    }

    public Call<DefaultResponse> registerUser(String name, String email, String password){
        return foodApiService.registerUser(name, email, password);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public LiveData<List<MenuItem>> getCartMenuItem(){
        return foodDao.getCartMenuItem();
    }

    private static class InsertMenuItemsTask extends AsyncTask<List<MenuItem>, Void, Void>{

        private FoodDao foodDao;
        private Application application;

        public InsertMenuItemsTask(FoodDao foodDao, Application application){
            this.foodDao = foodDao;
            this.application = application;
        }

        @Override
        protected Void doInBackground(List<MenuItem>... lists) {
            List<MenuItem> menuItems = lists[0];

            foodDao.deleteAllMenuItems();
            foodDao.insertAll(menuItems.toArray(new MenuItem[0]));

            return null;
        }
    }

    private static class UpdateMenuItemsTask extends AsyncTask<MenuItem, Void, Void>{

        private FoodDao foodDao;
        private Application application;

        public UpdateMenuItemsTask(FoodDao foodDao, Application application){
            this.foodDao = foodDao;
            this.application = application;
        }

        @Override
        protected Void doInBackground(MenuItem... lists) {
            MenuItem menuItem = lists[0];
            foodDao.update(menuItem);

            return null;
        }
    }
}
