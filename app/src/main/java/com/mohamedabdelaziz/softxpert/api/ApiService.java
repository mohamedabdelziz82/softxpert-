package com.mohamedabdelaziz.softxpert.api;

import com.mohamedabdelaziz.softxpert.model.CarsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;

import retrofit2.http.Query;


public interface ApiService {

    @GET("api/v1/cars")
    Single<CarsResponse> getData(@Query("page") int page);



}
