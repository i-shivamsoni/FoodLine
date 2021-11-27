package com.example.foodline.ui.auth.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LoginState<T> {

    @NonNull
    public final LoginStateStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable throwable;

    public LoginState(@NonNull LoginStateStatus status, @Nullable T data, @Nullable Throwable throwable) {
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> LoginState<T> authenticated (@Nullable T data) {
        return new LoginState<>(LoginStateStatus.AUTHENTICATED, data, null);
    }

    public static <T> LoginState<T> error(@NonNull Throwable throwable) {
        return new LoginState<>(LoginStateStatus.ERROR, null, throwable);
    }

    public static <T> LoginState<T> loading() {
        return new LoginState<>(LoginStateStatus.LOADING, null, null);
    }

    public static <T> LoginState<T> logout () {
        return new LoginState<>(LoginStateStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum LoginStateStatus {
        AUTHENTICATED,
        ERROR,
        LOADING,
        NOT_AUTHENTICATED
    }
}
