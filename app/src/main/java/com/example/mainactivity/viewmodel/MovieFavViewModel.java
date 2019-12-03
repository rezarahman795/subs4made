package com.example.mainactivity.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mainactivity.DB.movie.Favourite;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieFavViewModel extends ViewModel {


    private MutableLiveData<LinkedList<Favourite>> listFavMovies = new MutableLiveData<>();
    private final LinkedList<Favourite> listItemsMovie ;

    public MovieFavViewModel(LinkedList<Favourite> listItemsMovie) {
        this.listItemsMovie = listItemsMovie;
    }

    public void setMovieFragment() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=6acbbbb9dc49a42c7a5afa2490cd87b1&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject itemMovie = list.getJSONObject(i);
                        Favourite itemMovieView = new Favourite(itemMovie);
                        listItemsMovie.add(itemMovieView);
                    }

                    listFavMovies.postValue(listItemsMovie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<LinkedList<Favourite>> getMovies() {
        return listFavMovies;
    }
}
