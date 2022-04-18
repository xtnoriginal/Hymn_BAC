package com.example.hymn_bac.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hymn_bac.R;
import com.example.hymn_bac.databinding.FragmentHomeBinding;
import com.example.hymn_bac.recyclerview.RecyclerviewAdapter;
import com.example.hymn_bac.room.Data;
import com.example.hymn_bac.room.Hymn;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Hymn[] mDataSet;
    private static final int DATASET_COUNT = 60;
    RecyclerviewAdapter recyclerviewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


       recyclerviewAdapter = new RecyclerviewAdapter(Data.hymns);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.hymn_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(recyclerviewAdapter);

        SearchView  searchView = (SearchView) root.findViewById(R.id.search_hymn_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s.toUpperCase());
                return false;
            }
        });


        return root;
    }



    public void filter(String query){
        List<Hymn> list = new ArrayList<>();


        for (int i = 0; i < Data.hymns.length ; i++) {

            if(Data.hymns[i].getTitle().contains(query)){
                list.add(Data.hymns[i]);
            }

        }

        recyclerviewAdapter.filter(list);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}