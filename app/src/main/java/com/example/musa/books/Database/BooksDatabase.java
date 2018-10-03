package com.example.musa.books.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;




@Database(entities = {VolumeDatabase.class,FavouriteDatabase.class},version = 1,exportSchema = false)
public abstract class BooksDatabase extends RoomDatabase {
private  static BooksDatabase sInstance;
private  final static Object LOCK  =new Object();

private static final String dataBaseName="BOOKS";

    public static BooksDatabase getsInstance(Context context ) {
        if (sInstance==null){
            synchronized (LOCK){
                sInstance= Room
                        .databaseBuilder(context.getApplicationContext(),BooksDatabase.class,BooksDatabase.dataBaseName)
                        .build();
            }
        }
        return sInstance;
    }
    public abstract BooksDao booksDao();
    public abstract FavouriteDao favouriteDao();
}
