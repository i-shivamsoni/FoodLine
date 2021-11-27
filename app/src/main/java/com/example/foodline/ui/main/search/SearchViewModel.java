package com.example.foodline.ui.main.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodline.model.MenuItem;
import com.example.foodline.repository.FoodRepository;
import com.example.foodline.ui.main.menu.MenuState;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchViewModel extends AndroidViewModel {

    private final FoodRepository foodRepository;
    private final MutableLiveData<SearchState<List<MenuItem>>> searchState = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
        this.foodRepository = FoodRepository.getInstance(application);
    }

    public void getMenu(String query){

        foodRepository.getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MenuState<List<MenuItem>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull MenuState<List<MenuItem>> listMenuState) {
                        if(listMenuState != null){

                            switch (listMenuState.status) {

                                case LOADING:{
                                    searchState.setValue(SearchState.loading());
                                    break;
                                }

                                case SUCCESS:{
                                    if(listMenuState.data.size() > 0){
                                        List<MenuItem> searchedItems = new ArrayList<>();

                                        for(MenuItem menuItem: listMenuState.data){
                                            if(menuItem.getName().toLowerCase().startsWith(query.toLowerCase()) || menuItem.getCategory().toLowerCase().startsWith(query.toLowerCase())){
                                                searchedItems.add(menuItem);
                                            }
                                        }

                                        if(searchedItems.size() > 0 ){
                                            searchState.setValue(SearchState.hasItems(searchedItems));
                                        }else{
                                            searchState.setValue(SearchState.empty());
                                        }
                                    }else {
                                        searchState.setValue(SearchState.empty());
                                    }
                                    break;
                                }

                                case ERROR:{
                                    searchState.setValue(SearchState.error(listMenuState.throwable));
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void update(MenuItem menuItem){
        foodRepository.updateMenuItem(menuItem);
    }

    public MutableLiveData<SearchState<List<MenuItem>>> getSearchState() {
        return searchState;
    }
}
