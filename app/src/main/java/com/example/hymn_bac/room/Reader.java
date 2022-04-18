package com.example.hymn_bac.room;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Reader {

    public static void  addToDatabase(){



    }

/**
    public static ArrayList<Hymns> hymns(String s){
        s= s.replace('[',' ');
        s= s.replace(']',' ');
        s='['+s+']';
        ArrayList<Hymns> arrayList = new ArrayList<>();
        try{
            //JSONArray  from string
            JSONArray jsonArray = new JSONArray(s);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject)  jsonArray.get(i);

                arrayList.add(new Hymns(i,jsonObject.getString("name"), jsonObject.get("lyrics").toString()));



            }

        }catch (JSONException e){
            e.printStackTrace();
        }



        return arrayList;




    }
    public static Hymns[] readerJSON(Context context){

        String res ;
        try {

            InputStream inputStream = context.getAssets().open("output.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];
            inputStream.read(buffer);

            inputStream.close();

            res = new String(buffer, StandardCharsets.UTF_8);


        }catch (IOException e){

            e.printStackTrace();
            return null;
        }

        return  hymns(res).toArray(new Hymns[0]);

    }

 **/
}
