package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.APIUtils.movieApi;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
ImageView image;
    TextView mdate,mplot,mtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle=getIntent().getExtras();
        String date=bundle.getString("date");
        String plot=bundle.getString("plot");
        String imageString=bundle.getString("ImageString");
        String original=bundle.getString("original");
        image=(ImageView) findViewById(R.id.thumbnail_detail);
        mdate=(TextView) findViewById(R.id.date_deatil);
        mplot=(TextView) findViewById(R.id.plot);
        mtitle=(TextView) findViewById(R.id.name_deatil);

        Picasso.with(this).load(movieApi.THUMBNAIL+imageString).into(image);
        mdate.setText(date);
        mplot.setText(plot);
        mtitle.setText(original);

    }
}
