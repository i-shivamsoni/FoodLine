package com.example.foodline.ui.main.menu;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.FoodApiStatus;
import com.example.foodline.model.MenuItem;
import com.example.foodline.repository.FoodRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuViewModel extends AndroidViewModel {

    private static final String TAG = "MenuViewModel";

    private final FoodRepository foodRepository;

    private final MutableLiveData<MenuState<List<MenuItem>>> menuState = new MutableLiveData<>();
    private final CompositeDisposable disposable = new CompositeDisposable();

    public MenuViewModel(Application application){
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
//        this.menuItems = foodRepository.getMenuItems();
//        this.foodApiStatus = foodRepository.getFoodApiStatus();
    }

//    public MutableLiveData<FoodApiStatus> getFoodApiStatus() {
//        return foodApiStatus;
//    }
//
//    public MutableLiveData<List<MenuItem>> getMenuItems() {
//        return menuItems;
//    }
//
//    public MutableLiveData<List<MenuItem>> getSearchedMenuItems() {
//        return searchedMenuItems;
//    }

    public void getMenu(){
        foodRepository.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MenuState<List<MenuItem>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MenuState<List<MenuItem>> listMenuState) {
                        menuState.setValue(listMenuState);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void refreshMenu(){
        foodRepository.refreshMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MenuState<List<MenuItem>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MenuState<List<MenuItem>> listMenuState) {
                        menuState.setValue(listMenuState);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public MutableLiveData<MenuState<List<MenuItem>>> getMenuState() {
        return menuState;
    }

    public void update(MenuItem menuItem){
        foodRepository.updateMenuItem(menuItem);
    }

    //    public void update(MenuItem menuItem) {
//        foodRepository.updateMenuItem(menuItem);
//    }

//    public void refreshMenu() {
//        foodRepository.refreshMenu();
//    }
}
