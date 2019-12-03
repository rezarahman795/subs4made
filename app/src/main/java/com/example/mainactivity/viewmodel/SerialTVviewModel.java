package com.example.mainactivity.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mainactivity.model.SerialTV;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SerialTVviewModel extends ViewModel {

    private MutableLiveData<ArrayList<SerialTV>> listTV = new MutableLiveData<>();
    private final ArrayList<SerialTV> listItemsTV = new ArrayList<>();

    public void setTVFragment() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=6acbbbb9dc49a42c7a5afa2490cd87b1&language=en-US";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject itemTV = list.getJSONObject(i);
                        SerialTV itemTV_View = new SerialTV(itemTV);
                        listItemsTV.add(itemTV_View);
                    }

                    listTV.postValue(listItemsTV);
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

    public LiveData<ArrayList<SerialTV>> getSerialTV() {
        return listTV;
    }
}
