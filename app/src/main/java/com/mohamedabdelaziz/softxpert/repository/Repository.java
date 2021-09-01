package com.mohamedabdelaziz.softxpert.repository;

import com.mohamedabdelaziz.softxpert.api.ApiService;
import com.mohamedabdelaziz.softxpert.model.CarsResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;


public class Repository {

    private ApiService apiService;

    @Inject
    public Repository( ApiService apiService) {
        this.apiService = apiService;
    }


    public Single<CarsResponse> getData(int page){

        return apiService.getData(page);

    }
}
