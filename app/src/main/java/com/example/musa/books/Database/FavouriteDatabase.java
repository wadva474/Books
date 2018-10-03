package com.example.musa.books.Database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.musa.books.Dummy.VolumeInfo;

@Entity(tableName = "FavouriteBooks")
public class FavouriteDatabase {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String imageUrl;
    private String title;
    private String webLink;
    private String description;



    public FavouriteDatabase(int ID, String imageUrl, String title, String webLink, String description) {
        this.ID = ID;
        this.imageUrl = imageUrl;
        this.title = title;
        this.webLink = webLink;
        this.description = description;
    }
    @Ignore
    public FavouriteDatabase(String imageUrl, String title, String webLink, String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.webLink = webLink;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
