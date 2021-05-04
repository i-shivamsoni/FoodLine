package com.example.foodline.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.database.FoodDao;
import com.example.foodline.database.FoodDatabase;
import com.example.foodline.model.DefaultResponse;
import com.example.foodline.model.FoodApiStatus;
import com.example.foodline.model.MenuItem;
import com.example.foodline.network.FoodApiService;
import com.example.foodline.utils.ScreenUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRepository {

    private static FoodRepository INSTANCE;

    private FoodApiService foodApiService;
    private MutableLiveData<List<MenuItem>> menuItems;
    private MutableLiveData<FoodApiStatus> foodApiStatus;
    private Application application;
    private FoodDao foodDao;

    private ExecutorService executor;

    private FoodRepository(Application application){
        this.foodApiService = FoodApiService.getInstance();
        this.menuItems = new MutableLiveData<>();
        this.foodApiStatus = new MutableLiveData<>();
        this.application = application;
        this.foodDao = FoodDatabase.getInstance(application).foodDao();
        this.executor = Executors.newSingleThreadExecutor();

        initializeMenu();
    }

    public static FoodRepository getInstance(Application application){
        synchronized (FoodRepository.class){
            if(INSTANCE == null){
                INSTANCE = new FoodRepository(application);
            }
        }
        return INSTANCE;
    }

    private void initializeMenu(){
        Call<List<MenuItem>> call =  foodApiService.getMenu();
        foodApiStatus.setValue(FoodApiStatus.LOADING);

        call.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if(response.code() == 200){
                    menuItems.setValue(response.body());
                    foodApiStatus.setValue(FoodApiStatus.SUCCESS);
                    insertMenuItems(menuItems.getValue());
                }else{
                    foodApiStatus.setValue(FoodApiStatus.FAILURE);
                    Toast.makeText(application, "Something went wrong :(",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                if(!ScreenUtil.isInternetAvailable()){
                    foodApiStatus.setValue(FoodApiStatus.NO_INTERNET);
                    Toast.makeText(application, "No Internet Connection :(",Toast.LENGTH_SHORT).show();
                }else{
                    foodApiStatus.setValue(FoodApiStatus.FAILURE);
                    Toast.makeText(application, "Something went wrong :(",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public MutableLiveData<FoodApiStatus> getFoodApiStatus() {
        return foodApiStatus;
    }

    public MutableLiveData<List<MenuItem>> getMenuItems() {
        return menuItems;
    }

    public void insertMenuItems(List<MenuItem> menuItems) {
        executor.execute(() -> {
            foodDao.deleteAllMenuItems();
            foodDao.insertAll(menuItems.toArray(new MenuItem[0]));
        });
    }

    public void updateMenuItem(MenuItem menuItem){
        executor.execute(() -> {
            foodDao.update(menuItem);
        });
    }

    public Call<DefaultResponse> authenticateUser(String email, String password){
        return foodApiService.authenticateUser(email, password);
    }

    public Call<DefaultResponse> registerUser(String name, String email, String password){
        return foodApiService.registerUser(name, email, password);
    }

    public LiveData<List<MenuItem>> getCartMenuItem(){
        return foodDao.getCartMenuItem();
    }

    public void refreshMenu() {
        initializeMenu();
    }
}
