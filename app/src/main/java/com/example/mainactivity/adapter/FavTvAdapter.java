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
import com.example.mainactivity.DB.TV.FavTV;
import com.example.mainactivity.DB.movie.Favourite;
import com.example.mainactivity.R;

import java.util.List;

public class FavTvAdapter extends RecyclerView.Adapter<FavTvAdapter.ViewFavTvHolder> {
    private List<FavTV> favouritesTVList;
    Context context;

    public FavTvAdapter(List<FavTV> favTV, Context context) {
        this.favouritesTVList = favTV;
        this.context = context;
    }

    @NonNull
    @Override
    public FavTvAdapter.ViewFavTvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewFav_TV = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_tv, parent, false);
        return new ViewFavTvHolder(viewFav_TV);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewFavTvHolder holder, int position) {
        FavTV favourite = favouritesTVList.get(position);

        holder.txtNameSerial_fav.setText(favourite.getTitle());
        holder.txtTglSerial_fav.setText(favourite.getReleaseDate());
        holder.txtDescSerial_fav.setText(favourite.getOverview());
        holder.rateTV_fav.setRating((float)(favourite.getVoteAverage()));

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/original/"+favourite.getPosterPath())
                .placeholder((R.mipmap.ic_launcher))
                .apply(new RequestOptions().override(350,550))
                .into(holder.pictSerial_fav);
    }

    @Override
    public int getItemCount() {

        return favouritesTVList.size();
    }

    public class ViewFavTvHolder extends RecyclerView.ViewHolder {
        TextView txtNameSerial_fav,txtTglSerial_fav,txtDescSerial_fav;
        RatingBar rateTV_fav;
        ImageView pictSerial_fav;
        public ViewFavTvHolder(@NonNull View itemView) {
            super(itemView);


            pictSerial_fav=itemView.findViewById(R.id.item_poster_serial_fav);
            txtNameSerial_fav= itemView.findViewById(R.id.item_txt_name_serial_fav);
            txtTglSerial_fav= itemView.findViewById(R.id.item_txt_tgl_serial_fav);
            txtDescSerial_fav= itemView.findViewById(R.id.txt_desc_serial_fav);
            rateTV_fav = itemView.findViewById(R.id.TV_RATE_fav);
        }
    }
}
