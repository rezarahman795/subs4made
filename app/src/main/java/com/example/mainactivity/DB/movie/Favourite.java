package com.example.mainactivity.DB.movie;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
@Entity(tableName = "favourite")
public class Favourite {
    public Favourite() {

    }

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name="vote_average")
    private double voteAverage;

    @ColumnInfo(name= "poster_path")
    private String posterPath;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo (name = "release_date")
    private String releaseDate;


    public Favourite(JSONObject itemMovie) {
        try {
            Integer idMovie = itemMovie.getInt("id");
            String title = itemMovie.getString("original_title");
            String toDate = itemMovie.getString("release_date");
            double rating = itemMovie.getDouble("vote_average");
            String detailDesc = itemMovie.getString("overview");
            String poster = itemMovie.getString("poster_path");
            String backDrop = itemMovie.getString("backdrop_path");

            this.id = idMovie;
            this.title = title;
            this.releaseDate = toDate;
            this.overview = detailDesc;
            this.posterPath = poster;
            this.backdropPath = backDrop;
            this.voteAverage = rating;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
