package com.example.android.popularmovies;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.APIUtils.movieApi;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DetailActivity extends AppCompatActivity {
ImageView image,poster;
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
        String backdrop=bundle.getString("backdrop");
        Toolbar toolbar=(Toolbar) findViewById(R.id.beer);
        final AppBarLayout layout=(AppBarLayout) findViewById(R.id.appl);
        toolbar.setTitle("here toolbar");
        CollapsingToolbarLayout bar=(CollapsingToolbarLayout) findViewById(R.id.collap);
        bar.setTitle(original);
        bar.setExpandedTitleColor(getResources().getColor(R.color.white));

        image=(ImageView) findViewById(R.id.thumbnail_detail);
        mdate=(TextView) findViewById(R.id.date_deatil);
        mplot=(TextView) findViewById(R.id.plot);
        //mtitle=(TextView) findViewById(R.id.name_deatil);
        //poster=(ImageView) findViewById(R.id.poster_image);
        Picasso.with(this).load(movieApi.THUMBNAIL+backdrop).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                layout.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        Picasso.with(this).load(movieApi.THUMBNAIL+imageString).into(image);
        mdate.setText(date);
        mplot.setText(plot);
        //mtitle.setText(original);

    }
}
