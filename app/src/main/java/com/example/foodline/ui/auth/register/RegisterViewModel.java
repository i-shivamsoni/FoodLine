package com.example.foodline.ui.auth.register;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.model.User;
import com.example.foodline.repository.FoodRepository;
import com.example.foodline.utils.ScreenUtil;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends AndroidViewModel {

    private static final String TAG = "RegisterViewModel";

    private final FoodRepository foodRepository;

    private final MutableLiveData<RegisterState<User>> registerState = new MutableLiveData<>();
    private final CompositeDisposable disposable = new CompositeDisposable();

    public RegisterViewModel(Application application){
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
    }

    public void registerUser(String name, String email, String password){
        foodRepository.registerUser(name, email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterState<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull RegisterState<User> userRegisterState) {
                        registerState.setValue(userRegisterState);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public LiveData<RegisterState<User>> getRegisterState() {
        return registerState;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
