package com.mohamedabdelaziz.softxpert.di;

import android.net.TrafficStats;


import com.mohamedabdelaziz.softxpert.BuildConfig;
import com.mohamedabdelaziz.softxpert.api.ApiService;
import com.mohamedabdelaziz.softxpert.di.AddCookiesInterceptor;
import com.mohamedabdelaziz.softxpert.di.ReceivedCookiesInterceptor;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {


    @Singleton
    @Provides
    public OkHttpClient getOkHttpClient() {
        OkHttpClient  client =new OkHttpClient();
        if (client == null) {
            if (BuildConfig.DEBUG) {
                // set your desired log level
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = new OkHttpClient().newBuilder()
                               .addInterceptor(logging)
                        .connectTimeout(30, TimeUnit.SECONDS).build();
//                        .addInterceptor(logging)
//                        .build();
            } else {
                client = new OkHttpClient().newBuilder()
                        .build();
                TrafficStats.setThreadStatsTag(0x1000);

            }
        }
        return client;
    }
    @Provides
    @Singleton
    public static ApiService provideCarsService(OkHttpClient okHttpClient){

        return  new Retrofit.Builder()
                .baseUrl("https://demo1585915.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client( okHttpClient )
                .build()
                .create(ApiService.class);
    }



}
