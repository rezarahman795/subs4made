package com.example.mainactivity.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mainactivity.R;
import com.example.mainactivity.adapter.MovieAdapter;
import com.example.mainactivity.adapter.SerialTVAdapter;
import com.example.mainactivity.detail.SerialTVDetailActivity;
import com.example.mainactivity.model.Movie;
import com.example.mainactivity.model.SerialTV;
import com.example.mainactivity.viewmodel.SerialTVviewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SerialTVfragment extends Fragment {

    private RecyclerView rv_SerialTV;
    private ProgressBar pb_TV;
    private SerialTVAdapter sTV_Adapter;
    private ArrayList<SerialTV> listSerialTV = new ArrayList<>();


    public SerialTVfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_serial_tvfragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        rv_SerialTV = view.findViewById(R.id.rv_tv_serial);
        rv_SerialTV.setHasFixedSize(true);
        pb_TV = view.findViewById(R.id.loading_list_TV);


        showRecyclerSerialTV();
    }

    private void showRecyclerSerialTV() {

        SerialTVviewModel serialTVviewModel = ViewModelProviders.of(this).get(SerialTVviewModel.class);
        serialTVviewModel.getSerialTV().observe(this, getMovieObserve);

        sTV_Adapter = new SerialTVAdapter(listSerialTV);
        sTV_Adapter.notifyDataSetChanged();

        serialTVviewModel.setTVFragment();
        showLoadingFragment(true);

        rv_SerialTV.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_SerialTV.setAdapter(sTV_Adapter);


        sTV_Adapter.setOnItemClickCallback(new SerialTVAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(SerialTV dataSerial) {
                showSelectedSerialTV(dataSerial);
            }
        });
    }
    private Observer<ArrayList<SerialTV>> getMovieObserve = new Observer<ArrayList<SerialTV>>() {
        @Override
        public void onChanged(ArrayList<SerialTV> tvITEMS) {
            showLoadingFragment(true);

            if (tvITEMS != null) {
                sTV_Adapter.setDataTV(tvITEMS);
                showLoadingFragment(false);
            }
        }
    };

    private void showSelectedSerialTV(SerialTV detailDataSerialTV) {
        Intent serialTVintent = new Intent(getContext(), SerialTVDetailActivity.class);
        serialTVintent.putExtra("GET_DATA_SERIAL_TV", detailDataSerialTV);
        startActivity(serialTVintent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    private void showLoadingFragment(boolean state) {
        if (state) {
            pb_TV.setVisibility(View.VISIBLE);
        } else {
            pb_TV.setVisibility(View.GONE);
        }
    }


}
