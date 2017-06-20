package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.popularmovies.APIUtils.movieApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pussyhunter on 26/05/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    ArrayList<Movies> movie_list;
    Context context;
    public  MovieAdapter(Context context, ArrayList<Movies> movie_list){
        this.context=context;
        this.movie_list=movie_list;

    }
    public void swap(ArrayList<Movies> datas){
        movie_list.clear();
        movie_list.addAll(datas);
        notifyDataSetChanged();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
ImageView Thumbnail_image;
        RatingBar bar;

        public MovieHolder(View itemView) {
            super(itemView);
            Thumbnail_image=(ImageView) itemView.findViewById(R.id.thumbnail);
            bar=(RatingBar) itemView.findViewById(R.id.rating_star);
            //title=(TextView) itemView.findViewById(R.id.name);
            //rating=(TextView) itemView.findViewById(R.id.rating);
        }
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.moviethumbnail,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        MovieHolder holder=new MovieHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
Movies current_movie=movie_list.get(position);
        String poster_path=current_movie.getPosterpath();
        if(poster_path==null){
            holder.Thumbnail_image.setImageBitmap(current_movie.getBitmap());

        }
        else{
            Picasso.with(context).load(movieApi.THUMBNAIL +poster_path).into(holder.Thumbnail_image);

        }
        Float rate=(Float.valueOf(current_movie.getRating()))/2;
holder.bar.setRating(rate);
//        holder.title.setText(current_movie.getTitle());
  //      holder.rating.setText(current_movie.getRating());
if(movie_list.get(position).getDate()!=null) {
    holder.Thumbnail_image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("rating", movie_list.get(position).getRating());
            intent.putExtra("original", movie_list.get(position).getOriginal_title());
            intent.putExtra("date", movie_list.get(position).getDate());
            intent.putExtra("backdrop", movie_list.get(position).getBackdrop_path());
            intent.putExtra("plot", movie_list.get(position).getPlot());
            intent.putExtra("ImageString", movie_list.get(position).getPosterpath());
            intent.putExtra("id", movie_list.get(position).getId());
            //intent.putParcelableArrayListExtra("movie_list",movie_list);
            context.startActivity(intent);
        }
    });
}
    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }
}
