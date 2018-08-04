package com.example.musa.books.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.musa.books.Database.BooksDatabase;
import com.example.musa.books.Database.VolumeDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<VolumeDatabase>> booksLivedata;

    public MainViewModel(@NonNull Application application) {
        super(application);
        BooksDatabase booksDatabase=BooksDatabase.getsInstance(this.getApplication());
        booksLivedata=booksDatabase.booksDao().loadBooks();
    }

    public LiveData<List<VolumeDatabase>> getBooksLivedata() {
        return booksLivedata;
    }
}
