package com.example.android.popularmovies;

/**
 * Created by user on 6/20/2017.
 */

public class Reviews {

    String Review;
    String Reviewer;

    Reviews(String Review,String Reviewer){
        this.Review=Review;
        this.Reviewer=Reviewer;
    }

    public String getReview() {
        return Review;
    }

    public String getReviewer() {
        return Reviewer;
    }
}
