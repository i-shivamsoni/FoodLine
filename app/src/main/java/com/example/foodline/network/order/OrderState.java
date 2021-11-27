package com.example.foodline.network.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderState<T> {
    @NonNull
    public final OrderStateStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable throwable;

    public OrderState(@NonNull OrderStateStatus status, @Nullable T data, @Nullable Throwable throwable){
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> OrderState<T> loading(){
        return new OrderState<>(OrderStateStatus.LOADING,null,null);
    }

    public static <T> OrderState<T> success(T data){
        return new OrderState<>(OrderStateStatus.SUCCESS, data, null);
    }

    public static <T> OrderState<T> error(@NonNull Throwable throwable){
        return new OrderState<>(OrderStateStatus.ERROR, null, throwable);
    }

    public enum OrderStateStatus{
        LOADING,
        SUCCESS,
        ERROR
    }
}
