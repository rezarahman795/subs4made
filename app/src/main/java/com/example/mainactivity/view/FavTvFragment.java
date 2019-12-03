package com.example.mainactivity.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mainactivity.DB.TV.FavTV;
import com.example.mainactivity.DB.movie.Favourite;
import com.example.mainactivity.R;
import com.example.mainactivity.adapter.FavTvAdapter;

import java.util.List;

import static com.example.mainactivity.MainActivity.favTvDB;
import static com.example.mainactivity.MainActivity.favouriteDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavTvFragment extends Fragment {

    RecyclerView rv_TV_fav;
    public FavTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        rv_TV_fav = view.findViewById(R.id.rv_fav_tv);
        rv_TV_fav.setLayoutManager(new LinearLayoutManager(getContext()));

        getFavTV();

    }

    private void getFavTV() {
        List<FavTV> favTV = favTvDB.favTvDao().getDataFav();

        FavTvAdapter favTvAdapter= new FavTvAdapter(favTV, getContext());
        rv_TV_fav.setAdapter(favTvAdapter);


    }


}
