package com.example.mainactivity.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Movie implements Parcelable {
    private int movieID_DETAIL;
    private String descMovieDetail, detailNameMovie, tglMovieDetail, backdropPict, imageOrigin, genre;
    double star;

    public Movie(JSONObject objMovie) {
        try {
            Integer idMovie = objMovie.getInt("id");
            String title = objMovie.getString("original_title");
            String toDate = objMovie.getString("release_date");
            double rating = objMovie.getDouble("vote_average");
            String detailDesc = objMovie.getString("overview");
            String poster = objMovie.getString("poster_path");
            String backDrop = objMovie.getString("backdrop_path");

            this.movieID_DETAIL = idMovie;
            this.detailNameMovie = title;
            this.tglMovieDetail = toDate;
            this.descMovieDetail = detailDesc;
            this.imageOrigin = poster;
            this.backdropPict = backDrop;
            this.star = rating;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.movieID_DETAIL);
        dest.writeString(this.detailNameMovie);
        dest.writeString(this.tglMovieDetail);
        dest.writeString(this.descMovieDetail);
        dest.writeString(this.genre);
        dest.writeString(this.imageOrigin);
        dest.writeString(this.backdropPict);
        dest.writeDouble(this.star);
    }


    protected Movie(Parcel in) {
        this.movieID_DETAIL = in.readInt();
        this.detailNameMovie = in.readString();
        this.tglMovieDetail = in.readString();
        this.descMovieDetail = in.readString();
        this.genre = in.readString();
        this.imageOrigin = in.readString();
        this.backdropPict = in.readString();
        this.star = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
