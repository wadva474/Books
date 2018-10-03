package com.example.musa.books.Services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.example.musa.books.AppExecutors.AppExecutor;
import com.example.musa.books.Database.BooksDatabase;
import com.example.musa.books.Database.VolumeDatabase;
import com.example.musa.books.Dummy.AvailableBooks;
import com.example.musa.books.Retrofit.RetrofitBuilder;
import com.example.musa.books.Retrofit.UrlCall;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BooksSyncing {


    public static void bookSyncing(@NonNull final Context context) {

        Retrofit retrofit = RetrofitBuilder.Builder();
        UrlCall urlCall = retrofit.create(UrlCall.class);
        Call<AvailableBooks> Volumes = urlCall.volumes("Programming");
        Volumes.enqueue(new Callback<AvailableBooks>() {
            @Override
            public void onResponse(Call<AvailableBooks> call, Response<AvailableBooks> response) {
                assert response.body() != null;
                    try {
                            AvailableBooks availableBooks1=response.body();
                        assert availableBooks1 != null;
                        for (int k = 0; k< availableBooks1.getTotalItems(); k++) {
                                final String imageUrl = availableBooks1.getItems().get(k).getVolumeInfo().getImageLinks().getSmallThumbnail();
                                final String title = availableBooks1.getItems().get(k).getVolumeInfo().getTitle();
                                final String webLink = availableBooks1.getItems().get(k).getVolumeInfo().getPreviewLink();
                                final String description = availableBooks1.getItems().get(k).getVolumeInfo().getDescription();

                                AppExecutor.getSinstance().getDataIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        BooksDatabase
                                                .getsInstance(context)
                                                .booksDao()
                                                .insertBooks(new VolumeDatabase(imageUrl, title, webLink, description));
                                    }
                                });
                            }


                    }
                    catch (Exception e ){
                        e.printStackTrace();
                    }

                }


            @Override
            public void onFailure(Call<AvailableBooks> call, Throwable t) {
              Toast.makeText(context ,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }

}

