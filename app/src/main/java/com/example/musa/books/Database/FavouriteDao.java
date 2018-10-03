package com.example.musa.books.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import java.util.List;


@Dao
public interface FavouriteDao {
    @android.arch.persistence.room.Query("SELECT * FROM FavouriteBooks")
    LiveData<List<FavouriteDatabase>>loadFavourite();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(FavouriteDatabase favouriteDatabase);
}
