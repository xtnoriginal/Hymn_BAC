package com.example.hymn_bac.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HymnDao {


    @Insert
    void insertAll(Hymn... hymns);

    @Query("SELECT * FROM hymn")
    List<Hymn> getAll();

    @Query("SELECT * FROM hymn WHERE id IN (:ids)")
    List<Hymn> loadAllByIds(int[] ids);


    @Query("SELECT * FROM hymn WHERE favourite = 'true'")
    List<Hymn> getFavourite();

    @Update
    void update(Hymn hymn);
}
