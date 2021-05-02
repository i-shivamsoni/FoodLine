package com.example.foodline.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodline.model.DefaultResponse;
import com.example.foodline.repository.FoodRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<Boolean> isRegistered = new MutableLiveData<>();

    public RegisterViewModel(){
        this.foodRepository = new FoodRepository();
    }

    public void registerUser(){
        Call<DefaultResponse> call = foodRepository.registerUser(name.getValue(), email.getValue(), password.getValue());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                isRegistered.setValue(true);
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
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
