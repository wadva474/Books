package com.example.musa.books.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.musa.books.Dummy.VolumeInfo;

import java.util.List;

@Dao
public interface BooksDao {
    @Query("SELECT * FROM VolumeInfo")
    LiveData<List<VolumeDatabase>> loadBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBooks(VolumeDatabase volumeInfoList);
}
