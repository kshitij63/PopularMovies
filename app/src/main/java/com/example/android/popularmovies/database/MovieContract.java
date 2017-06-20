package com.example.android.popularmovies.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by user on 6/20/2017.
 */

public class MovieContract {

    public static final String AUTHORITIES="com.example.android.popularmovies";
    public static final Uri BASE_URI=Uri.parse("content://" +AUTHORITIES);
    public static final String MOVIE_TASK="movies";

   public  static class movietable implements BaseColumns{

        public static final Uri CONTENT_URI=BASE_URI.buildUpon().appendPath(MOVIE_TASK).build();

        public static final String TABLE_NAME="movies";
        public static final String ID=BaseColumns._ID;
        public static final String MOVIE_IMAGE="name";
        public static final String MOVIE_RATING="rating";
    }
}
