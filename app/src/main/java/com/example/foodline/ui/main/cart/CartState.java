package com.example.foodline.ui.main.cart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CartState<T> {

    @NonNull
    public final CartStateStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable throwable;

    private CartState(@NonNull CartStateStatus status, @Nullable T data, @Nullable Throwable throwable){
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> CartState<T> empty(){
        return new CartState<>(CartStateStatus.EMPTY, null, null);
    }

    public static <T> CartState<T> hasItems(@NonNull T data){
        return new CartState<>(CartStateStatus.HAS_ITEMS, data, null);
    }

    public static <T> CartState<T> error(@NonNull Throwable throwable){
        return new CartState<>(CartStateStatus.ERROR, null, throwable);
    }

    public enum CartStateStatus{
        EMPTY,
        HAS_ITEMS,
        ERROR
    }
}
