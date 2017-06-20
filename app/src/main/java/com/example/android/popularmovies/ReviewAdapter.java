package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 6/20/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    ArrayList<Reviews> reviewses;
    Context context;

    ReviewAdapter(Context context,ArrayList<Reviews> reviewses){
        this.context=context;
        this.reviewses=reviewses;
    }
    class ReviewHolder extends RecyclerView.ViewHolder{
TextView textView,textView2;
        public ReviewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.reviewer_name);
            textView2=(TextView) itemView.findViewById(R.id.review);
        }
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.reviews,parent,false);
        ReviewHolder holder=new ReviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
holder.textView.setText(reviewses.get(position).getReviewer());
        holder.textView2.setText(reviewses.get(position).getReview());
    }

    @Override
    public int getItemCount() {
        return reviewses.size();
    }
}
