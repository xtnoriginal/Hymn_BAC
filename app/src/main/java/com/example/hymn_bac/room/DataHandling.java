package com.example.hymn_bac.room;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataHandling {

    private HymnDao hymnDao;

    public DataHandling(AppDatabase database, Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
               hymnDao = database.userDao();
            }
        }).start();

    }


    public  List<Hymn> getAll(){
        return hymnDao.getAll();
    }




    public void getFavourite(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Hymn> list = Data.dataHandling.getAll();
                List<Hymn> fav = new ArrayList<>();

                for (int i = 0; i < list.size() ; i++) {

                    if(list.get(i).favourite){
                        Log.i("hello",list.get(i).title);
                        fav.add(list.get(i));
                    }
                }

                Data.favourites = fav;

            }
        }).start();


    }


    public void update(Hymn hymn){

        new Thread(new Runnable() {
            @Override
            public void run() {
                hymnDao.update(hymn);
            }
        }).start();

    }





/**
    public void addToDatabase(){
        Data.hymns =  Reader.readerJSON(context);

         new Thread(new Runnable() {
            public void run() {

                Hymn[] hms = new Hymn[Data.hymns.length];
                for (int i = 0; i < Data.hymns.length; i++) {
                    Hymn temp = new Hymn();
                    temp.favourite = false;
                    temp.id = i+1;
                    temp.title = Data.hymns[i].getName();
                    temp.lyrics = Data.hymns[i].getLyrics();
                    hms[i] = temp;

         }

         hymnDao.insertAll(hms);
         Log.i("da", hymnDao.getAll().size()+"");
         }
         }).start();
    }
 **/
}
