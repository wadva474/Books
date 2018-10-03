package com.example.musa.books.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class BooksService extends IntentService {

    public BooksService() {
        super("BooksService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
     BooksSyncing.bookSyncing(this);
    }
}
