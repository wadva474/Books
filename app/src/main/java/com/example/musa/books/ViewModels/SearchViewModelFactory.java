package com.example.musa.books.ViewModels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.musa.books.Database.BooksDatabase;

public class SearchViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final BooksDatabase mDb;
    private final String mSearch;

    public SearchViewModelFactory(BooksDatabase mDb, String mSearch) {
        this.mDb = mDb;
        this.mSearch = mSearch;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new SearchViewModel(mDb,mSearch);
    }
}
