package com.example.musa.books.Retrofit;

import com.example.musa.books.Dummy.AvailableBooks;
import com.example.musa.books.Dummy.VolumeInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UrlCall {
    @GET("volumes")
    Call<AvailableBooks> volumes (@Query("q") String Volume);
}
