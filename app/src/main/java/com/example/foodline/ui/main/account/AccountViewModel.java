package com.example.foodline.ui.main.account;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.User;
import com.example.foodline.repository.FoodRepository;
import com.example.foodline.utils.SharedPreferenceUtil;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AccountViewModel extends AndroidViewModel {

    private final FoodRepository foodRepository;
    private final SharedPreferenceUtil sharedPreferenceUtil;

    private final MutableLiveData<User> userData = new MutableLiveData<>();

    public AccountViewModel(@NonNull Application application) {
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
        this.sharedPreferenceUtil = SharedPreferenceUtil.getInstance(application);

        getUser();
    }

    private void getUser() {
        foodRepository.getUser().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull User user) {
                        userData.setValue(user);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setIsLogin(boolean isLogin){
        if(!isLogin){
            sharedPreferenceUtil.setIsLogin(isLogin);
            foodRepository.deleteUser();
        }else{
            sharedPreferenceUtil.setIsLogin(isLogin);
        }
    }

    public MutableLiveData<User> getUserData() {
        return userData;
    }
}
