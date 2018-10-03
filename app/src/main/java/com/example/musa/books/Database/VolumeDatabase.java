package com.example.musa.books.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.musa.books.Dummy.VolumeInfo;
@Entity(tableName = "VolumeInfo")
public class VolumeDatabase {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String imageUrl;
    private String title;
    private String webLink;
    private String description;



    public VolumeDatabase(int ID, String imageUrl, String title, String webLink, String description) {
        this.ID = ID;
        this.imageUrl = imageUrl;
        this.title = title;
        this.webLink = webLink;
        this.description = description;
    }
    @Ignore
    public VolumeDatabase(String imageUrl, String title, String webLink, String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.webLink = webLink;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl() {
        this.imageUrl = new VolumeInfo().getImageLinks().getSmallThumbnail();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle() {
        this.title = new VolumeInfo().getTitle();
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink() {
        this.webLink = new VolumeInfo().getPreviewLink();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        this.description = new VolumeInfo().getDescription();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
