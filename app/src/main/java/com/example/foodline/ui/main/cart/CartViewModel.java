package com.example.foodline.ui.main.cart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.MenuItem;
import com.example.foodline.model.Order;
import com.example.foodline.network.order.OrderState;
import com.example.foodline.repository.FoodRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartViewModel extends AndroidViewModel {

    private static final String TAG = "CartViewModel";

    private final FoodRepository foodRepository;
    private final MutableLiveData<CartState<List<MenuItem>>> cartState = new MutableLiveData<>();
    private final MutableLiveData<Float> grandTotal = new MutableLiveData<>();
    private final MutableLiveData<OrderState> orderStateLiveData = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
    }

    public void getCartMenuItems() {
        foodRepository.getCartMenuItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartState<List<MenuItem>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull CartState<List<MenuItem>> listCartState) {
                        calculateGrandTotal(listCartState.data);
                        cartState.setValue(listCartState);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void calculateGrandTotal(List<MenuItem> data) {
        if(data != null){

            float total = 0;

            for(MenuItem cartItem: data){
                total += Float.parseFloat(cartItem.getPrice()) * cartItem.getCounterInCart();
            }

            grandTotal.setValue(total);
        }
    }

    public void update(MenuItem menuItem) {
        foodRepository.updateMenuItem(menuItem);
    }

    public void addOrder(Order order) {
        foodRepository.addOrder(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderState>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull OrderState orderState) {
                        orderStateLiveData.setValue(orderState);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public LiveData<CartState<List<MenuItem>>> getCartState() {
        return cartState;
    }

    public LiveData<Float> getGrandTotal() {
        return grandTotal;
    }

    public LiveData<OrderState> getOrderStateLiveData() {
        return orderStateLiveData;
    }
}
