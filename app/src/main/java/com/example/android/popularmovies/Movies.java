package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by pussyhunter on 26/05/2017.
 */

public class Movies implements Parcelable {
    String title;
    String rating;
    String posterpath;
    String date;
    String Backdrop_path;
    String plot;
    String original_title;

    Movies(String title, String rating, String posterpath,String date,String plot,String original_title,String Backdrop_path){
        this.Backdrop_path=Backdrop_path;
        this.title=title;
        this.posterpath=posterpath;
        this.rating=rating;
        this.date=date;
        this.original_title=title;
        this.plot=plot;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPlot() {
        return plot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getBackdrop_path() {
        return Backdrop_path;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
dest.writeString(title);
        dest.writeString(rating);
        dest.writeString(posterpath);
        dest.writeString(original_title);
        dest.writeString(plot);
        dest.writeString(date);
    }

    private Movies(Parcel in){
        title=in.readString();
        rating=in.readString();
        posterpath=in.readString();
        original_title=in.readString();
        plot=in.readString();
        date=in.readString();

    }

    public static final Parcelable.Creator<Movies> CREATOR
            = new Parcelable.Creator<Movies>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
