package com.example.mainactivity.adapter;

import android.content.Context;
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
import com.example.mainactivity.DB.movie.Favourite;
import com.example.mainactivity.R;
import java.util.ArrayList;
import java.util.List;


public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.ViewFavMovieHolder> {

    private List<Favourite> favMovieList;
    Context context;
    private final onItemClickListener onItemClickListener;


    public FavMovieAdapter(List<Favourite>listFavMovie,onItemClickListener listener, Context context) {
        this.onItemClickListener = listener;
        this.favMovieList = listFavMovie;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setDataMovie(List<Favourite>listDataMovie){
        favMovieList.clear();
        favMovieList.addAll(listDataMovie);
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public FavMovieAdapter.ViewFavMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewFav_Movie = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_movie, parent, false);
        return new ViewFavMovieHolder(viewFav_Movie);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewFavMovieHolder holder, int position) {
        Favourite favourite = favMovieList.get(position);

        holder.txtNameMovies_fav.setText(favourite.getTitle());
        holder.txtTglMovies_fav.setText(favourite.getReleaseDate());
        holder.rateMovie_fav.setRating((float) (favourite.getVoteAverage() / 2));
        holder.txtDescMovie_Fav.setText(favourite.getOverview());

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/" + favourite.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .apply(new RequestOptions().override(350, 550))
                .into(holder.pictMovies_fav);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClicked(favMovieList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {

        return favMovieList.size();
    }

    public class ViewFavMovieHolder extends RecyclerView.ViewHolder {
        TextView txtNameMovies_fav, txtTglMovies_fav, txtDescMovie_Fav;
        ImageView pictMovies_fav;
        RatingBar rateMovie_fav;

        public ViewFavMovieHolder(@NonNull View itemView) {
            super(itemView);

            pictMovies_fav = itemView.findViewById(R.id.item_poster_movie_fav);
            txtNameMovies_fav = itemView.findViewById(R.id.item_txt_name_movie_fav);
            txtTglMovies_fav = itemView.findViewById(R.id.item_txt_tgl_movie_fav);
            txtDescMovie_Fav = itemView.findViewById(R.id.item_txt_desc_movie_fav);
            rateMovie_fav = itemView.findViewById(R.id.movie_rate_fav);
        }
    }

    public interface onItemClickListener {

        void onItemClicked(Favourite dataFavMovie);
    }
}
