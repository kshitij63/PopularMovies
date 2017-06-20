package com.example.android.popularmovies;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.popularmovies.APIUtils.movieApi;
import com.example.android.popularmovies.database.MoviedbHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RequestQueue queue;
    TextView errortext;
MovieAdapter adapter;
    GridLayoutManager manager;
    Toolbar toolbar;
    ArrayList<Movies> movie_list;
    RecyclerView view;
    ProgressBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view=(RecyclerView) findViewById(R.id.movie_recycle);
        queue= Volley.newRequestQueue(this);
errortext=(TextView) findViewById(R.id.error);
        toolbar=(Toolbar) findViewById(R.id.tool_main);
        setSupportActionBar(toolbar);
        manager=new GridLayoutManager(this,2);
if(savedInstanceState!=null){
    movie_list=savedInstanceState.getParcelableArrayList("list");
    view.setAdapter(new MovieAdapter(MainActivity.this,movie_list));
    view.setLayoutManager(manager);
    manager.scrollToPosition(savedInstanceState.getInt("index"));
}
else{
    movie_list=new ArrayList<>();
    bar=(ProgressBar) findViewById(R.id.pb);
    bar.setVisibility(View.VISIBLE);
    MovieRequest(movieApi.MOVIE_TOP_RATED);

}
        //Log.e("sink",movieApi.key);
     if(isOnline()){
         errortext.setVisibility(View.VISIBLE);
     }


    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void MovieRequest(String type_path){
        bar.setVisibility(View.VISIBLE);
        movie_list.clear();
        queue.start();

        //view.removeAllViews();
        //adapter.notifyDataSetChanged();
        //movie_list.clear();
        //Picasso.with(MainActivity.this).load(movieApi.THUMBNAIL +poster_path).into(imageView);
        JsonObjectRequest mrequest=new JsonObjectRequest(Request.Method.GET, type_path+getString(R.string.movie_key), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                errortext.setVisibility(View.INVISIBLE);
        //        Toast.makeText(MainActivity.this,"swing",Toast.LENGTH_SHORT).show();
                bar.setVisibility(View.INVISIBLE);
                try {
                    JSONArray results=response.getJSONArray("results");
                    for(int i=0;i<results.length();i++){
                        JSONObject movie=results.getJSONObject(i);
                        String poster_path=movie.getString("poster_path");
                        String title=movie.getString("title");
                        String id=movie.getString("id");
                        String rating= String.valueOf(movie.getDouble("vote_average"));
                        String plot=movie.getString("overview");
                        String date=movie.getString("release_date");
                        String original_title=movie.getString("original_title");
                        String Backdrop_path=movie.getString("backdrop_path");
                        Movies m=new Movies(title,rating,poster_path,date,plot,original_title,Backdrop_path,id);
                        movie_list.add(m);
                    }
                    adapter=new MovieAdapter(MainActivity.this,movie_list);
                    //adapter.swap(movie_list);
                    queue.stop();
                    view.setAdapter(adapter);
                    view.setLayoutManager(manager);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
bar.setVisibility(View.INVISIBLE);
                errortext.setVisibility(View.INVISIBLE);
            }
        });

        queue.add(mrequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected_item=item.getItemId();
        if(selected_item==R.id.top_rated){
            MovieRequest(movieApi.MOVIE_TOP_RATED);

        }
        else if(selected_item==R.id.popular){
            MovieRequest(movieApi.MOVIE_POPULAR);
        }
        else if(selected_item==R.id.fav){
            Intent intent=new Intent(MainActivity.this,FavouriteActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("list",movie_list);
        outState.putInt("index",manager.findFirstVisibleItemPosition());
    }
}
