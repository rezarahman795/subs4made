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
import com.example.mainactivity.detail.MovieDetailActivity;
import com.example.mainactivity.model.Movie;
import com.example.mainactivity.viewmodel.MovieViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView rv_movies;
    private ArrayList<Movie> listMovie = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private ProgressBar pb_rvMovie;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        rv_movies = view.findViewById(R.id.rv_movie);
        pb_rvMovie = view.findViewById(R.id.loading_list_movie);

        showRecyclerMovie();
    }


    private void showRecyclerMovie() {

        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovieObserve);

        movieAdapter = new MovieAdapter(listMovie);
        movieAdapter.notifyDataSetChanged();

        movieViewModel.setMovieFragment();
        showLoadingFragment(true);

        rv_movies.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_movies.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie dataMovie) {

                showSelectedMovie(dataMovie);

            }
        });
    }

    private Observer<ArrayList<Movie>> getMovieObserve = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movieItems) {
            showLoadingFragment(true);

            if (movieItems != null) {
                movieAdapter.setDataMovie(movieItems);
                showLoadingFragment(false);
            }
        }
    };

    private void showLoadingFragment(boolean state) {
        if (state) {
            pb_rvMovie.setVisibility(View.VISIBLE);
        } else {
            pb_rvMovie.setVisibility(View.GONE);
        }
    }

    private void showSelectedMovie(Movie detailDataMovie) {
        Intent movieDetail = new Intent(getContext(), MovieDetailActivity.class);
        movieDetail.putExtra("GET_DATA_MOVIE", detailDataMovie);
        startActivity(movieDetail);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}
