package com.example.musa.books.ViewModels;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import com.example.musa.books.Database.BooksDatabase;
import com.example.musa.books.Database.VolumeDatabase;

import java.util.ArrayList;

public class SearchViewModel extends ViewModel {
    private LiveData<ArrayList<VolumeDatabase>> searchLiveData;

    public SearchViewModel(BooksDatabase booksDatabase,String searchedBook) {
        searchLiveData = booksDatabase.booksDao().searchBooks(searchedBook);
    }

    public LiveData<ArrayList<VolumeDatabase>> getSearchLiveData() {
        return searchLiveData;
    }
}
