package com.example.hymn_bac.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hymn_bac.R;
import com.example.hymn_bac.databinding.FragmentDashboardBinding;
import com.example.hymn_bac.recyclerview.RecyclerviewAdapter;
import com.example.hymn_bac.room.Data;
import com.example.hymn_bac.room.DataHandling;
import com.example.hymn_bac.room.Hymn;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    static Hymn[] mDataSet;
    private static final int DATASET_COUNT = 60;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Data.dataHandling.getFavourite();

        initDataset();

        RecyclerviewAdapter recyclerviewAdapter;
        if(mDataSet ==null){

            recyclerviewAdapter = new RecyclerviewAdapter(new Hymn[0]);
            Toast.makeText(getContext(), "No Favourites", Toast.LENGTH_LONG).show();
        }else{
            recyclerviewAdapter = new RecyclerviewAdapter(mDataSet);
        }


        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.hymn_recyclerview_favourites);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(recyclerviewAdapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }






    private void initDataset(){


        mDataSet = new Hymn[Data.favourites.size()];
        for (int i = 0; i <Data.favourites.size(); i++) {
            mDataSet[i] = Data.favourites.get(i);
        }

    }

}