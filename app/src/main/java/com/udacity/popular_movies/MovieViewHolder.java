package com.udacity.popular_movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popular_movies.com.udacity.popular_movies.task.BaseUrlTask;

import java.util.concurrent.ExecutionException;

import info.movito.themoviedbapi.model.MovieDb;

public class MovieViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private MovieDb movie;
    private ListItemClickListener mOnClickListener = null;
    private final View view;
    private final Context context;

    public MovieViewHolder(Context context, View itemView, ListItemClickListener mOnClickListener) {
        super(itemView);
        this.view = itemView.findViewById(R.id.image_item);
        this.mOnClickListener = mOnClickListener;
        this.context = context;
    }

    void bind(MovieDb movie) throws ExecutionException, InterruptedException {
        ImageView imageView = view.findViewById(R.id.image_item);
        imageView.setOnClickListener(this);
        this.movie = movie;
        String path = movie.getPosterPath();
        AsyncTask<Void, Void, String> baseUrlTask = new BaseUrlTask().execute();
        String baseUrl = baseUrlTask.get();
        String completepath = baseUrl + "w185/" + path;
        Picasso.with(context)
                .load(completepath)
                .into(imageView);
        view.refreshDrawableState();
    }

    @Override
    public void onClick(View v) {
        mOnClickListener.onListItemClick(movie.getId());
    }
}