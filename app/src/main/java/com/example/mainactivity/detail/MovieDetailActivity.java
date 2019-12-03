package com.example.mainactivity.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mainactivity.DB.movie.Favourite;
import com.example.mainactivity.R;
import com.example.mainactivity.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;
import static com.example.mainactivity.MainActivity.favouriteDB;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "GET_DATA_MOVIE";
    private int id;
    private TextView tvNameMovie, tvTglMovie, tvDescMovie, tvGenreMovie;
    private RatingBar rateDetailMovie;
    private ImageView posterMovie, backDropMovie;
    private String movieNameDetail, movieTglDetail, movieDescDetail, imgDetail, imgBackDrop;
    private ProgressBar pbMovie;
    private ScrollView sViewDetail;
    private float rateDetailMovie1;
    private static final String API_KEY = "6acbbbb9dc49a42c7a5afa2490cd87b1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        onBindViewDeclare();
        getDataMovie();
        setDataMovie();

    }

    private void onBindViewDeclare() {

        rateDetailMovie = findViewById(R.id.RATE_MOVIE_detail);
        tvGenreMovie = findViewById(R.id.genre_movie_detail);
        posterMovie = findViewById(R.id.image_detail_movie);
        backDropMovie = findViewById(R.id.backdrop_detail_movie);
        pbMovie = findViewById(R.id.loading_movie_detail);
        sViewDetail = findViewById(R.id.scrollView);
        tvNameMovie = findViewById(R.id.title_detail_movie);
        tvTglMovie = findViewById(R.id.tgl_detail_movie);
        tvDescMovie = findViewById(R.id.detail_description_movie);
    }

    private void getDataMovie() {
        Intent moveIntent = getIntent();
        Movie dataMovie = moveIntent.getParcelableExtra(EXTRA_MOVIE);
        id = Objects.requireNonNull(getIntent().getExtras().getInt(EXTRA_MOVIE));

        if (dataMovie != null) {
            getDetailDataMovie(dataMovie.getMovieID_DETAIL());
            id = dataMovie.getMovieID_DETAIL();
            System.out.println("id movie : "+dataMovie.getMovieID_DETAIL());
            movieNameDetail = dataMovie.getDetailNameMovie();
            System.out.println("data name : " + dataMovie.getDetailNameMovie());
            movieTglDetail = dataMovie.getTglMovieDetail();
            System.out.println("data tgl: " + dataMovie.getTglMovieDetail());
            movieDescDetail = dataMovie.getDescMovieDetail();
            imgDetail = dataMovie.getImageOrigin();
            System.out.println("data picture 1 : " + dataMovie.getImageOrigin());
            imgBackDrop = dataMovie.getBackdropPict();
            System.out.println("data picture 2 : " + dataMovie.getBackdropPict());
            rateDetailMovie1 = (float) dataMovie.getStar();

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(dataMovie.getDetailNameMovie());
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
    }

    private void setDataMovie() {

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/" + imgBackDrop)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(backDropMovie);

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/" + imgDetail)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(posterMovie);

        tvNameMovie.setText(movieNameDetail);
        tvTglMovie.setText(movieTglDetail);
        tvDescMovie.setText(movieDescDetail);
        rateDetailMovie.setRating(rateDetailMovie1 / 2);
    }

    private void getDetailDataMovie(int idMovie) {
        String url = "https://api.themoviedb.org/3/movie/" + idMovie + "?api_key=" + API_KEY + "&language=en-US";

        AsyncHttpClient mClient = new AsyncHttpClient();
        mClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                pbMovie.setVisibility(View.GONE);
                sViewDetail.setVisibility(View.VISIBLE);
                try {
                    String responeAPI = new String(responseBody);
                    JSONObject objectAPI = new JSONObject(responeAPI);

                    JSONArray jsonArray = objectAPI.getJSONArray("genres");
                    List<String> genreList = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String genreName = jsonObject.getString("name");
                        genreList.add(genreName);
                    }
                    String genres = TextUtils.join(", ", genreList);
                    tvGenreMovie.setText(genres);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("onFailure", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favourite_icon, menu);
        if (favouriteDB.favouriteDao().x_Favourite(id)==1) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites));
            menu.getItem(0).setChecked(true);
        } else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites));
            menu.getItem(0).setChecked(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Favourite favMovie = new Favourite();

        favMovie.setId(id);
        favMovie.setOverview(movieDescDetail);
        favMovie.setBackdropPath(imgBackDrop);
        favMovie.setPosterPath(imgDetail);
        favMovie.setReleaseDate(movieTglDetail);
        favMovie.setTitle(movieNameDetail);
        favMovie.setVoteAverage(rateDetailMovie1);

        if (item.getItemId() == R.id.favorite_icon) {
            if (item.isChecked()) {
                favouriteDB.favouriteDao().delete(favMovie);
                Toast.makeText(this, R.string.removed_from_favorite, Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.ic_add_to_favorites);
            } else {
                favouriteDB.favouriteDao().addDataFav(favMovie);
                Toast.makeText(this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show();
                item.setIcon(R.drawable.ic_added_to_favorites);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
