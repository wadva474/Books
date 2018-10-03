package com.example.musa.books.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitBuilder {
    public static  Retrofit Builder(){
        return new Retrofit.Builder()
                .baseUrl( BaseUrl.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
