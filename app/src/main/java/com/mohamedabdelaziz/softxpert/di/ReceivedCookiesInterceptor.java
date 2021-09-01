package com.mohamedabdelaziz.softxpert.di;

import android.content.Context;
import android.content.SharedPreferences;


import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;

public class ReceivedCookiesInterceptor implements Interceptor {
    private   HashSet<String> cookies   = new HashSet<>();
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {


            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);

            }
                    }
        return originalResponse;
    }


}