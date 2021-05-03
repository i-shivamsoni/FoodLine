package com.example.foodline.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.repository.FoodRepository;
import com.example.foodline.utils.ScreenUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Boolean> isRegistered = new MutableLiveData<>();

    public RegisterViewModel(Application application){
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
    }

    public void registerUser(){
        Call<DefaultResponse> call = foodRepository.registerUser(name.getValue(), email.getValue(), password.getValue());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if(response.code() == 200) {
                    Toast.makeText(getApplication(), "Registered Successfully:)", Toast.LENGTH_SHORT).show();
                    isRegistered.setValue(true);
                }else if(response.code() == 400){
                    Toast.makeText(getApplication(), "You are already registered :)", Toast.LENGTH_SHORT).show();
                    isRegistered.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                if(!ScreenUtils.isInternetAvailable()){
                    Toast.makeText(getApplication(), "Check your internet connection and try again :(", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(), "Something went wrong :(", Toast.LENGTH_SHORT).show();
                }
                isRegistered.setValue(false);
                t.printStackTrace();
            }
        });
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<Boolean> getIsRegistered() {
        return isRegistered;
    }
}
