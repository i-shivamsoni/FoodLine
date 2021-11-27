package com.example.foodline.ui.admin.notifications;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.Order;
import com.example.foodline.network.order.OrderState;
import com.example.foodline.repository.FoodRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NotificationsViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private MutableLiveData<OrderState<List<Order>>> orderState = new MutableLiveData<>();
    private MutableLiveData<OrderState<String>> acceptedOrderState = new MutableLiveData<>();

    private final String TAG = this.getClass().getSimpleName();

    public NotificationsViewModel(Application application) {
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
    }

    public void getAcceptedOrders() {
        foodRepository.getAcceptedOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderState<List<Order>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull OrderState<List<Order>> listOrderState) {
                        orderState.setValue(listOrderState);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "err  " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void acceptOrder(String id) {
        foodRepository.acceptOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderState<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull OrderState<String> orderState) {
                        acceptedOrderState.setValue(orderState);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public LiveData<OrderState<List<Order>>> getOrderState() {
        return orderState;
    }

    public LiveData<OrderState<String>> getOrderAcceptedState() {
        return acceptedOrderState;
    }

}
