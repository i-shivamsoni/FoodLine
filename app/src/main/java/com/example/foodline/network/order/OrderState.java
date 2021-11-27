package com.example.foodline.network.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderState {

    @NonNull
    public final OrderStateStatus status;

    @Nullable
    public final Throwable throwable;

    public OrderState(@NonNull OrderStateStatus status, @Nullable Throwable throwable){
        this.status = status;
        this.throwable = throwable;
    }

    public static OrderState loading(){
        return new OrderState(OrderStateStatus.LOADING,null);
    }

    public static OrderState success(){
        return new OrderState(OrderStateStatus.SUCCESS, null);
    }

    public static OrderState error(@NonNull Throwable throwable){
        return new OrderState(OrderStateStatus.ERROR, throwable);
    }

    public enum OrderStateStatus{
        LOADING,
        SUCCESS,
        ERROR
    }
}
