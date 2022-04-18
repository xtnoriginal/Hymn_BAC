package com.example.hymn_bac.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hymn_bac.R;
import com.example.hymn_bac.room.Data;


public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = Data.hymns.length;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        Intent i =getIntent();
        int pos= i.getIntExtra("position",1);
        Log.i("receive",pos+"");

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);

        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setPageTransformer(new DepthPageTransformer());
        viewPager.setAdapter(pagerAdapter);

        viewPager.setCurrentItem(pos);
    }
    
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            Log.i("oo",position+"");
            return new ScreenSlidePageFragment(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}