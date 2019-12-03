package com.example.mainactivity.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mainactivity.DB.movie.Favourite;
import com.example.mainactivity.R;
import com.example.mainactivity.adapter.FavMovieAdapter;
import com.example.mainactivity.detail.MovieDetailActivity;
import com.example.mainactivity.viewmodel.MovieFavViewModel;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.example.mainactivity.MainActivity.favouriteDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment implements FavMovieAdapter.onItemClickListener {
    private List<Favourite> favMovies = favouriteDB.favouriteDao().getDataFav();
    private RecyclerView rv_Fav_movie;
    private ProgressBar pb_rvMovie;
    private FavMovieAdapter favMovieAdapter;

    public FavMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        rv_Fav_movie = view.findViewById(R.id.rv_fav_movie);
        pb_rvMovie = view.findViewById(R.id.loading_list_movie);
        rv_Fav_movie.setLayoutManager(new LinearLayoutManager(getContext()));
        showRecyclerFavMovie();

    }

    private void showRecyclerFavMovie() {

        MovieFavViewModel movieFavViewModel= ViewModelProviders.of(this).get(MovieFavViewModel.class);
        movieFavViewModel.getMovies().observe(this, getMovieFavObserve);

        favMovieAdapter = new FavMovieAdapter(favMovies,this,getContext());
        favMovieAdapter.notifyDataSetChanged();

        rv_Fav_movie.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_Fav_movie.setAdapter(favMovieAdapter);

        movieFavViewModel.setMovieFragment();
        showLoadingFragment(true);



//        getFavMovie();
    }

    private Observer<LinkedList<Favourite>> getMovieFavObserve = new Observer<LinkedList<Favourite>>() {
        @Override
        public void onChanged(LinkedList<Favourite> movieFavItems) {
            showLoadingFragment(true);

            if (movieFavItems != null) {
                favMovieAdapter.setDataMovie(movieFavItems);
                showLoadingFragment(false);
            }
        }
    };

//    private void getFavMovie() {
//
//        favMovieAdapter = new FavMovieAdapter(favMovies, this, getContext());
//        favMovieAdapter.notifyDataSetChanged();
//        rv_Fav_movie.setAdapter(favMovieAdapter);
//
//
//    }
    @Override
    public void onItemClicked(Favourite dataFavMovie) {
        showSelectedFavMovie(dataFavMovie);
    }


    private void showSelectedFavMovie(Favourite dataFavMovie) {
        Intent intentFavMovie = new Intent(getContext(), MovieDetailActivity.class);
        intentFavMovie.putExtra(MovieDetailActivity.EXTRA_MOVIE, dataFavMovie.getId());
        startActivityForResult(intentFavMovie,0);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    private void showLoadingFragment(boolean state) {
        if (state) {
            pb_rvMovie.setVisibility(View.VISIBLE);
        } else {
            pb_rvMovie.setVisibility(View.GONE);
        }
    }



}
