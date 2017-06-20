package com.example.android.popularmovies.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by user on 6/20/2017.
 */

public class MovieProvider extends ContentProvider {

    private static final int MOVIES=100;
    private static final int MOVIES_WITH_ID=101;
private static final UriMatcher mMatcher=makeMatcher();
    public static UriMatcher makeMatcher(){
        UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(MovieContract.AUTHORITIES,MovieContract.MOVIE_TASK,MOVIES);
        matcher.addURI(MovieContract.AUTHORITIES,MovieContract.MOVIE_TASK +"/#",MOVIES_WITH_ID);

        return matcher;
    }

    MoviedbHelper helper;
    @Override
    public boolean onCreate() {
        helper=new MoviedbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db=helper.getReadableDatabase();

        int match=mMatcher.match(uri);
        Cursor cr = null;
        if(match==MOVIES){
            cr=db.query(MovieContract.movietable.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);

        }

        cr.setNotificationUri(getContext().getContentResolver(),uri);


        return cr;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db=helper.getWritableDatabase();
        int match=mMatcher.match(uri);
        Uri return_uri = null;
        if(match==MOVIES){
            long id=db.insert(MovieContract.movietable.TABLE_NAME,null,values);
            if(id>0)
            return_uri= ContentUris.withAppendedId(MovieContract.movietable.CONTENT_URI,id);
            else{
                Toast.makeText(getContext(),"error inserting",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
        }
        getContext().getContentResolver().notifyChange(uri,null);

        return return_uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db=helper.getWritableDatabase();
        int match=mMatcher.match(uri);
        int row = 0;
        if(match==MOVIES_WITH_ID){
            String id=uri.getLastPathSegment();
            String mselection="_id=?";
            String mselectionagr[]=new String[]{id};
            row=db.delete(MovieContract.movietable.TABLE_NAME,mselection,mselectionagr);
        }
        getContext().getContentResolver().notifyChange(uri,null);

        return row;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
