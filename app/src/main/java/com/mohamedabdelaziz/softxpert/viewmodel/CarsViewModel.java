package com.mohamedabdelaziz.softxpert.viewmodel;


import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.mohamedabdelaziz.softxpert.model.Cars;
import com.mohamedabdelaziz.softxpert.model.CarsResponse;
import com.mohamedabdelaziz.softxpert.repository.Repository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CarsViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    private Repository repository;
    private MutableLiveData<CarsResponse> carsMutableLiveData = new MutableLiveData<CarsResponse>();
    public MutableLiveData<String> errorsLiveData = new MutableLiveData<>();

    @ViewModelInject
    public CarsViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<CarsResponse> getCarsList() {
        return carsMutableLiveData;
    }


    public void getCarsData(int page){

        repository.getData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> carsMutableLiveData.setValue(result)
                        ,
                        error-> errorsLiveData.setValue(error.getMessage()));

    }




}
