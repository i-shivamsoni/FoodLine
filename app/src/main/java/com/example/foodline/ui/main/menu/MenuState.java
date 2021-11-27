package com.example.foodline.ui.main.menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MenuState<T> {

    @NonNull
    public final MenuStateStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable throwable;

    public MenuState(@NonNull MenuStateStatus status, @Nullable T data, @Nullable Throwable throwable){
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> MenuState<T> loading(){
        return new MenuState<>(MenuStateStatus.LOADING, null, null);
    }

    public static <T> MenuState<T> success(@NonNull T data){
        return new MenuState<>(MenuStateStatus.SUCCESS, data, null);
    }

    public static <T> MenuState<T> error(@NonNull Throwable throwable){
        return new MenuState<>(MenuStateStatus.ERROR, null, throwable);
    }
    
    public enum MenuStateStatus{
        LOADING,
        SUCCESS,
        ERROR
    }
}
