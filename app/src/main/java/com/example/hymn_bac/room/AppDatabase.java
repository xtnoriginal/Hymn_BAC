package com.example.hymn_bac.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Hymn.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HymnDao userDao();
}
