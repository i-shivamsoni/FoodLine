package com.example.foodline.ui.auth.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RegisterState<T> {
    @NonNull
    public final RegisterStateStatus status;

    @Nullable
    public final Throwable throwable;

    public RegisterState(@NonNull RegisterStateStatus status, @Nullable Throwable throwable) {
        this.status = status;
        this.throwable = throwable;
    }

    public static <T> RegisterState<T> registered () {
        return new RegisterState<>(RegisterStateStatus.REGISTERED, null);
    }

    public static <T> RegisterState<T> error(@NonNull Throwable throwable) {
        return new RegisterState<>(RegisterStateStatus.ERROR, throwable);
    }

    public static <T> RegisterState<T> loading() {
        return new RegisterState<>(RegisterStateStatus.LOADING, null);
    }

    public enum RegisterStateStatus {
        REGISTERED,
        ERROR,
        LOADING;
    }
}
