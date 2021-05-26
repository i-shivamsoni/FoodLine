package com.example.foodline.ui.main.search;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchState<T> {

    @NonNull
    public final SearchStateStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final Throwable throwable;

    private SearchState(@NonNull SearchStateStatus status, @Nullable T data, @Nullable Throwable throwable){
        this.status = status;
        this.data = data;
        this.throwable = throwable;
    }

    public static <T> SearchState<T> loading(){
        return new SearchState<>(SearchStateStatus.LOADING, null, null);
    }

    public static <T> SearchState<T> empty(){
        return new SearchState<>(SearchStateStatus.EMPTY, null, null);
    }

    public static <T> SearchState<T> hasItems(@NonNull T data){
        return new SearchState<>(SearchStateStatus.HAS_ITEMS, data, null);
    }

    public static <T> SearchState<T> error(@NonNull Throwable throwable){
        return new SearchState<>(SearchStateStatus.ERROR, null, throwable);
    }

    public enum SearchStateStatus{
        LOADING,
        EMPTY,
        HAS_ITEMS,
        ERROR
    }
}
