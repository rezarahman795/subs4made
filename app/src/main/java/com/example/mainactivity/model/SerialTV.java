package com.example.mainactivity.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RatingBar;

import org.json.JSONObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SerialTV implements Parcelable {

    private int ID_TV;
    private String serialNameTV,tglSerial,descSerial,backdropPictTV,pictureTV;
    double rateTV;

    public SerialTV(JSONObject objTV){
        try {
            Integer idMovieTV = objTV.getInt("id");
            String titleTV = objTV.getString("original_name");
            String toDateTV = objTV.getString("first_air_date");
            double ratingTV = objTV.getDouble("vote_average");
            String detailDescTV = objTV.getString("overview");
            String posterTV = objTV.getString("poster_path");
            String backDropTV = objTV.getString("backdrop_path");

            this.ID_TV = idMovieTV;
            this.serialNameTV = titleTV;
            this.tglSerial = toDateTV;
            this.descSerial = detailDescTV;
            this.pictureTV = posterTV;
            this.backdropPictTV = backDropTV;
            this.rateTV = ratingTV;

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
        dest.writeInt(ID_TV);
        dest.writeString(pictureTV);
        dest.writeString(backdropPictTV);
        dest.writeString(serialNameTV);
        dest.writeString(tglSerial);
        dest.writeString(descSerial);
        dest.writeDouble(rateTV);
    }
    protected SerialTV(Parcel in) {
        ID_TV = in.readInt();
        pictureTV = in.readString();
        backdropPictTV = in.readString();
        serialNameTV = in.readString();
        tglSerial = in.readString();
        descSerial = in.readString();
        rateTV = in.readDouble();
    }

    public static final Creator<SerialTV> CREATOR = new Creator<SerialTV>() {
        @Override
        public SerialTV createFromParcel(Parcel in) {
            return new SerialTV(in);
        }

        @Override
        public SerialTV[] newArray(int size) {
            return new SerialTV[size];
        }
    };
}
