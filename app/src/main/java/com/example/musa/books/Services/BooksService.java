package com.example.musa.books.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.musa.books.ActivityandFragment.MyBooksAvailableRecyclerViewAdapter;
import com.example.musa.books.AppExecutors.AppExecutor;
import com.example.musa.books.Database.BooksDatabase;
import com.example.musa.books.Database.VolumeDatabase;
import com.example.musa.books.Dummy.AvailableBooks;
import com.example.musa.books.Dummy.VolumeInfo;
import com.example.musa.books.Retrofit.RetrofitBuilder;
import com.example.musa.books.Retrofit.UrlCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BooksService extends IntentService {

    public BooksService() {
        super("BooksService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
     BooksSyncing.BookSyncing(this);
    }
}
