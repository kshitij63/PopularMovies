package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 6/20/2017.
 */

public class Reviews implements Parcelable {

    String Review;
    String Reviewer;

    Reviews(String Review,String Reviewer) {
        this.Review = Review;
        this.Reviewer = Reviewer;
    }


    protected Reviews(Parcel in) {
        Review = in.readString();
        Reviewer = in.readString();
    }

    public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel in) {
            return new Reviews(in);
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    };

    public String getReview() {
        return Review;
    }

    public String getReviewer() {
        return Reviewer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Review);
        dest.writeString(Reviewer);
    }
}
