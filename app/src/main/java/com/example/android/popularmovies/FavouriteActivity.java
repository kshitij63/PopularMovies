package com.example.android.popularmovies;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.database.MovieContract;
import com.example.android.popularmovies.database.MoviedbHelper;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager manager;
    ArrayList<Movies> moviesArrayList;
    Bitmap bitmap;
    String rating;
    TextView view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        MoviedbHelper helper=new MoviedbHelper(this);
        SQLiteDatabase db=helper.getReadableDatabase();
        view=(TextView) findViewById(R.id.nothing_to_show);
        recyclerView=(RecyclerView) findViewById(R.id.movie_recycle);
        manager=new GridLayoutManager(this,2);
        if(savedInstanceState!=null){
            moviesArrayList=savedInstanceState.getParcelableArrayList("list");
            recyclerView.setAdapter(new MovieAdapter(FavouriteActivity.this,moviesArrayList));
            recyclerView.setLayoutManager(manager);
            manager.scrollToPosition(savedInstanceState.getInt("index"));
        }
        else{
            moviesArrayList=new ArrayList<>();
            myAsync async=new myAsync();
            async.execute();
        }


    }

    class myAsync extends AsyncTask<Void,Void,Cursor>{

        @Override
        protected Cursor doInBackground(Void... params) {

            Cursor cr=getContentResolver().query(MovieContract.movietable.CONTENT_URI,null,null,null,null);


            return cr;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if(cursor.getCount()==0){
                recyclerView.setVisibility(View.INVISIBLE);
                view.setVisibility(View.VISIBLE);
            }
            else{
                recyclerView.setVisibility(View.VISIBLE);
                view.setVisibility(View.INVISIBLE);
                if(cursor.moveToFirst()){
                    do{

                        bitmap=getBitmap(cursor.getBlob(cursor.getColumnIndex(MovieContract.movietable.MOVIE_IMAGE)));
                        rating=cursor.getString(cursor.getColumnIndex(MovieContract.movietable.MOVIE_RATING));
                        Movies movies=new Movies(rating,bitmap);
                        moviesArrayList.add(movies);
                    }
                    while (cursor.moveToNext());
                }
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(new MovieAdapter(getApplicationContext(),moviesArrayList));

            }
            }


    }

    public Bitmap getBitmap(byte[] array) {
         Bitmap mbitmap= BitmapFactory.decodeByteArray(array,0,array.length);
        return mbitmap;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list",moviesArrayList);
        outState.putInt("index",manager.findFirstVisibleItemPosition());
    }
}
