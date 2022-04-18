package com.example.hymn_bac.recyclerview;

/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hymn_bac.R;
import com.example.hymn_bac.room.Data;
import com.example.hymn_bac.room.Hymn;
import com.example.hymn_bac.viewpager.ScreenSlidePagerActivity;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private Hymn[] mDataSet;



    public void filter(List<Hymn> hymnsList){

        Hymn[] dataset = new Hymn[hymnsList.size()];

        for (int i = 0; i < hymnsList.size() ; i++) {
            dataset[i] = hymnsList.get(i);
        }

        mDataSet = dataset;
        notifyDataSetChanged();


    }


    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private int position =0 ;
        ImageButton imageButton;
        Hymn hymn ;
        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    Bundle bundle= new Bundle();

                    bundle.putInt("position",position);
                    Intent intent = new Intent(v.getContext(), ScreenSlidePagerActivity.class);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);


                }
            });
            textView = (TextView) v.findViewById(R.id.textView);

            imageButton = (ImageButton) v.findViewById(R.id.imageView);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(hymn.favourite == true){

                        hymn.favourite = false;
                        imageButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        Data.dataHandling.update(hymn);

                    }else{
                        hymn.favourite = true;
                        imageButton.setImageResource(R.drawable.ic_baseline_favorite_24);
                        Data.dataHandling.update(hymn);
                    }


                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public RecyclerviewAdapter(Hymn[] dataSet) {
        mDataSet = dataSet;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hymnview_layout_row, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet[position].getTitle());
        viewHolder.position = mDataSet[position].getId() -1;
        viewHolder.hymn = mDataSet[position];

        if(mDataSet[position].favourite == false){

            viewHolder.imageButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }else{

            viewHolder.imageButton.setImageResource(R.drawable.ic_baseline_favorite_24);

        }

        //favouritesChangeData();

    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)


    public void  favouritesChangeData(){

        Data.dataHandling.getFavourite();

        mDataSet = new Hymn[Data.favourites.size()];
        for (int i = 0; i <Data.favourites.size(); i++) {
            mDataSet[i] = Data.favourites.get(i);
        }

        //notifyDataSetChanged();

    }


   public void  changeDataSetFavourites(){
        Data.dataHandling.getFavourite();
        mDataSet = new Hymn[Data.favourites.size()];
       for (int i = 0; i <Data.favourites.size(); i++) {
           mDataSet[i] = Data.favourites.get(i);
       }
   }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }



}