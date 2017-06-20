package com.example.android.popularmovies.APIUtils;

import android.content.res.Resources;

import com.example.android.popularmovies.R;

/**
 * Created by pussyhunter on 26/05/2017.
 */

public class movieApi {
   //public static String key= (String) Resources.getSystem().getText(R.string.movie_key);

    public static final String MOVIE_POPULAR= "http://api.themoviedb.org/3/movie/popular?api_key=";

    public static final String MOVIE_TOP_RATED= "http://api.themoviedb.org/3/movie/top_rated?api_key=";
    public static final String MOVIE_REVIEWS_1= "http://api.themoviedb.org/3/movie/";
            public static final String MOVIE_REVIEW_2="/reviews?api_key=";
    public static final String MOVIE_TRAILER_2="/trailers?api_key=";


    public static final String THUMBNAIL="http://image.tmdb.org/t/p/w185/";


}
