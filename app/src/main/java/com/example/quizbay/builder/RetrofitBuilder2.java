package com.example.quizbay.builder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder2 {
    private static Retrofit instance;

    private RetrofitBuilder2() {
        //private constructor
    }

    public static Retrofit getInstance() {
        if(instance == null) {
            synchronized (RetrofitBuilder2.class) {
                if(instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl("http://10.177.1.113:8082/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient())
                            .build();
                }
            }
        }
        return instance;
    }

}
