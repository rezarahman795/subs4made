package com.example.mainactivity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mainactivity.R;
import com.example.mainactivity.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie>listMovie ;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public MovieAdapter(ArrayList<Movie> draftMovie) {

        this.listMovie = draftMovie;
    }

    public void setDataMovie(ArrayList<Movie>listDataMovie){
        listMovie.clear();
        listMovie.addAll(listDataMovie);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {

        Movie myMovie =listMovie.get(position);

        holder.txtNameMovies.setText(myMovie.getDetailNameMovie());
        holder.txtTglMovies.setText(myMovie.getTglMovieDetail());
        holder.rateMovie.setRating((float)(myMovie.getStar()/2));
        holder.txtDescMovie.setText(myMovie.getDescMovieDetail());

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/"+myMovie.getImageOrigin())
                .placeholder(R.mipmap.ic_launcher)
                .apply(new RequestOptions().override(350,550))
                .into(holder.pictMovies);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
            }
        });
    }


    @Override
    public int getItemCount() {

        return listMovie.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameMovies,txtTglMovies,txtDescMovie;
        ImageView pictMovies;
        RatingBar rateMovie;

        MovieViewHolder(View itemView) {
            super(itemView);

            pictMovies =itemView.findViewById(R.id.item_poster_movie);
            txtNameMovies = itemView.findViewById(R.id.item_txt_name_movie);
            txtTglMovies = itemView.findViewById(R.id.item_txt_tgl_movie);
            txtDescMovie = itemView.findViewById(R.id.item_txt_desc_movie);
            rateMovie = itemView.findViewById(R.id.movie_rate);

        }
    }
    public interface OnItemClickCallback {

        void onItemClicked(Movie dataMovie);
    }
}
