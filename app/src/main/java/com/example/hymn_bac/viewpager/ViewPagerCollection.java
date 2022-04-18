package com.example.hymn_bac.viewpager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.example.hymn_bac.R;


public class ViewPagerCollection extends Fragment {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    ViewPagerAdapter demoCollectionAdapter;
    ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        demoCollectionAdapter = new ViewPagerAdapter(this);
        //viewPager = view.findViewById(R.id.pager);
        //viewPager.setAdapter(demoCollectionAdapter);
    }
}
