package com.example.hymn_bac.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hymn_bac.R;
import com.example.hymn_bac.room.Data;


public class ScreenSlidePageFragment extends Fragment {
    private int pos;


    public ScreenSlidePageFragment(int pos){
        super();
        this.pos=pos;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = (ViewGroup) inflater.inflate(
                 R.layout.fragment_screen_slide_page, container, false);

        TextView titleView= (TextView) root.findViewById(R.id.hymn_title_view);
        titleView.setText(Data.hymns[pos].getTitle());

        TextView lyricsView= (TextView) root.findViewById(R.id.lyrics_view);
        lyricsView.setText(Data.hymns[pos].getLyrics());
        return root;
    }
}