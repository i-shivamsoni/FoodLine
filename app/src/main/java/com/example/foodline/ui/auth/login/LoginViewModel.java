package com.example.foodline.ui.auth.login;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.model.User;
import com.example.foodline.repository.FoodRepository;
import com.example.foodline.utils.ScreenUtil;
import com.example.foodline.utils.SharedPreferenceUtil;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = "LoginViewModel";

    private final FoodRepository foodRepository;
    private final SharedPreferenceUtil sharedPreferenceUtil;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<LoginState<User>> loginState = new MutableLiveData<>();

    public LoginViewModel(Application application) {
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
        this.sharedPreferenceUtil = SharedPreferenceUtil.getInstance(application);
    }
    
    public void authenticateUser(String email, String password){
        foodRepository.authenticateUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginState<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull LoginState<User> userLoginState) {
                        loginState.setValue(userLoginState);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public LiveData<LoginState<User>> getLoginState() {
        return loginState;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public void setIsLogin(boolean isLogin) {
        if(isLogin){
            sharedPreferenceUtil.setIsLogin(isLogin);
        }
    }
}
