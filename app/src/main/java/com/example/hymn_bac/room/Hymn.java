package com.example.hymn_bac.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hymn {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "lyrics")
    public String lyrics;

    @ColumnInfo(name = "favourite")
    public boolean favourite;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public boolean isFavourite() {
        return favourite;
    }
}
