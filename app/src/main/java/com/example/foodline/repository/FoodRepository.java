package com.example.foodline.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.foodline.database.FoodDao;
import com.example.foodline.database.FoodDatabase;
import com.example.foodline.database.menu.MenuCacheMapper;
import com.example.foodline.database.user.UserCacheMapper;
import com.example.foodline.model.MenuItem;
import com.example.foodline.model.User;
import com.example.foodline.network.FoodApiService;
import com.example.foodline.network.menu.MenuNetworkEntity;
import com.example.foodline.network.menu.MenuNetworkMapper;
import com.example.foodline.network.user.UserNetworkEntity;
import com.example.foodline.network.user.UserNetworkMapper;
import com.example.foodline.ui.auth.login.LoginState;
import com.example.foodline.ui.auth.register.RegisterState;
import com.example.foodline.ui.main.cart.CartState;
import com.example.foodline.ui.main.menu.MenuState;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class FoodRepository {

    private static final String TAG = "FoodRepository";
    private static FoodRepository INSTANCE;

    private final Application application;

    private final FoodApiService foodApiService;
    private final FoodDao foodDao;
    private final UserCacheMapper userCacheMapper;
    private final UserNetworkMapper userNetworkMapper;
    private final MenuCacheMapper menuCacheMapper;
    private final MenuNetworkMapper menuNetworkMapper;

    private final ExecutorService executor;
    private final Handler handler;

    private MenuState<List<MenuItem>> menuState;

    private FoodRepository(Application application){
        this.application = application;
        this.foodApiService = FoodApiService.getInstance();
        this.foodDao = FoodDatabase.getInstance(application).foodDao();
        this.userCacheMapper = new UserCacheMapper();
        this.userNetworkMapper = new UserNetworkMapper();
        this.menuCacheMapper = new MenuCacheMapper();
        this.menuNetworkMapper = new MenuNetworkMapper();
        this.executor = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper());
    }

    public static FoodRepository getInstance(Application application){
        synchronized (FoodRepository.class){
            if(INSTANCE == null){
                INSTANCE = new FoodRepository(application);
            }
        }
        return INSTANCE;
    }

    public Observable<LoginState<User>> authenticateUser(String email, String password){

        return Observable.create(emitter -> {
            emitter.onNext(LoginState.loading());
            Log.d(TAG, "authenticateUser: Authenticating...");

            foodApiService.authenticateUser(email, password)
                    .subscribe(new Observer<UserNetworkEntity>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull UserNetworkEntity userNetworkEntity) {
                            User user = userNetworkMapper.mapFromEntity(userNetworkEntity);
                            foodDao.insert(userCacheMapper.mapToEntity(user));
                            emitter.onNext(LoginState.authenticated(userCacheMapper.mapFromEntity(foodDao.getUser())));

                            Log.d(TAG, "onNext: Authenticated");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            emitter.onNext(LoginState.error(e));
                            Log.d(TAG, "onError: Error " + e);
                        }

                        @Override
                        public void onComplete() {
                            emitter.onComplete();
                        }
                    });
        });
    }

    public Observable<RegisterState<User>> registerUser(String name, String email, String password){
        return Observable.create(emitter -> {
            emitter.onNext(RegisterState.loading());
            Log.d(TAG, "registerUser: Registering...");

            foodApiService.registerUser(name, email, password)
                    .subscribe(new Observer<UserNetworkEntity>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull UserNetworkEntity userNetworkEntity) {
                            emitter.onNext(RegisterState.registered());
                            Log.d(TAG, "onNext: Registered");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            emitter.onNext(RegisterState.error(e));
                            Log.d(TAG, "onError: Error " + e.toString());
                        }

                        @Override
                        public void onComplete() {
                            emitter.onComplete();
                        }
                    });

        });
    }

    public Observable<MenuState<List<MenuItem>>> getMenu(){

        if(menuState != null && menuState.data != null && menuState.data.size() > 0){
            return Observable.create(emitter -> {
                try{
                    menuState = MenuState.success(menuCacheMapper.mapFromEntityList(foodDao.getMenu()));
                    emitter.onNext(menuState);
                }catch (Exception e){
                    emitter.onNext(MenuState.error(e));
                }
            });
        }

        return Observable.create(emitter -> {
            emitter.onNext(MenuState.loading());

            foodApiService.getMenu()
                    .retry(3)
                    .subscribe(new Observer<List<MenuNetworkEntity>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull List<MenuNetworkEntity> dishNetworkEntities) {
                            List<MenuItem> menuItems = menuNetworkMapper.mapFromEntityList(dishNetworkEntities);

                            for(MenuItem menuItem: menuItems){
                                foodDao.insert(menuCacheMapper.mapToEntity(menuItem));
                            }

                            menuState = MenuState.success(menuCacheMapper.mapFromEntityList(foodDao.getMenu()));

                            emitter.onNext(menuState);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            emitter.onNext(MenuState.error(e));
                        }

                        @Override
                        public void onComplete() {
                            emitter.onComplete();
                        }
                    });
        });
    }

    public Observable<MenuState<List<MenuItem>>> refreshMenu(){
        return Observable.create(emitter -> {
            emitter.onNext(MenuState.loading());

            foodApiService.getMenu()
                    .retry(3)
                    .subscribe(new Observer<List<MenuNetworkEntity>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull List<MenuNetworkEntity> dishNetworkEntities) {
                            List<MenuItem> menuItems = menuNetworkMapper.mapFromEntityList(dishNetworkEntities);

                            for(MenuItem menuItem: menuItems){
                                if(menuState != null && menuState.data != null && menuState.data.size() > 0) {
                                    for(MenuItem item: menuState.data){
                                        if(menuItem.getId() == item.getId()){
                                            menuItem.setCounterInCart(item.getCounterInCart());
                                        }
                                    }
                                }
                                foodDao.insert(menuCacheMapper.mapToEntity(menuItem));
                            }

                            menuState = MenuState.success(menuCacheMapper.mapFromEntityList(foodDao.getMenu()));
                            emitter.onNext(MenuState.success(menuCacheMapper.mapFromEntityList(foodDao.getMenu())));
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            emitter.onNext(MenuState.error(e));
                        }

                        @Override
                        public void onComplete() {
                            emitter.onComplete();
                        }
                    });
        });
    }

    public void updateMenuItem(MenuItem menuItem){
        executor.execute(() -> {
            foodDao.update(menuCacheMapper.mapToEntity(menuItem));
            menuState = MenuState.success(menuCacheMapper.mapFromEntityList(foodDao.getMenu()));
        });
    }

    private void showToast(String text) {
        Toast.makeText(application, text, Toast.LENGTH_SHORT).show();
    }

    public Observable<CartState<List<MenuItem>>> getCartMenuItem(){
        return Observable.create(emitter -> {
            try {
                List<MenuItem> menuItems = menuCacheMapper.mapFromEntityList(foodDao.getCartMenuItem());

                if(menuItems.size() > 0){
                    emitter.onNext(CartState.hasItems(menuItems));
                }else{
                    emitter.onNext(CartState.empty());
                }
            }catch (Exception e){
                emitter.onNext(CartState.error(e));
            }
        });
    }

    public void deleteMenu() {
        executor.execute(() -> foodDao.deleteAllMenuItems());
    }

    public Observable<User> getUser(){
        return Observable.create(emitter -> {
            try {
                emitter.onNext(userCacheMapper.mapFromEntity(foodDao.getUser()));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void deleteUser(){
        executor.execute(() -> foodDao.deleteUser());
    }
}
