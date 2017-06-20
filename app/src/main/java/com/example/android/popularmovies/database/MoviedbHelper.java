package com.example.android.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 6/20/2017.
 */

public class MoviedbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="movie.db";
    private static final int DATABASE_VERSION=1;

    public MoviedbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
  String create_table= "create table " +MovieContract.movietable.TABLE_NAME+ "(" +
          MovieContract.movietable.ID +" integer primary key autoincrement," +
          MovieContract.movietable.MOVIE_IMAGE+ " blob," +
          MovieContract.movietable.MOVIE_RATING +" text)";
        db.execSQL(create_table);
        Log.e("this",create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
