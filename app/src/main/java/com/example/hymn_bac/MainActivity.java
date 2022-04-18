package com.example.hymn_bac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.hymn_bac.room.AppDatabase;
import com.example.hymn_bac.room.Data;
import com.example.hymn_bac.room.DataHandling;
import com.example.hymn_bac.room.Hymn;
import com.example.hymn_bac.room.HymnDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppDatabase db =Room.databaseBuilder(this, AppDatabase.class, "hymns.db")
                .createFromAsset("hymn-database")
                .build();



        Data.dataHandling = new DataHandling(db,this);


        new Thread(new Runnable() {
            @Override
            public void run() {
                HymnDao hymnDao = db.userDao();
                List<Hymn> x = hymnDao.getAll();

                Data.hymns = new Hymn[x.size()];

                for (int i = 0; i <x.size() ; i++) {
                    Hymn temp = x.get(i);
                    hymnDao.update(temp);
                    Data.hymns[i] = temp;
                }



                Data.favourites = hymnDao.getAll();

            }
        }).start();


        //Data.hymns =  Reader.readerJSON(this.getBaseContext());

        /**
        new Thread(new Runnable() {
            public void run() {
                HymnDao hymnDao = db.userDao();
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
     **/







        Intent intent = new Intent(this,HymnListView.class);
        //Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
        finish();

    }
}