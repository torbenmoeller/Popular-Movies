package com.udacity.popular_movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.concurrent.ExecutionException;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Torben on 26.02.18.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    View view;
    private ListItemClickListener mOnClickListener = null;
    private Context context;

    public MovieViewHolder(Context context, View itemView, ListItemClickListener mOnClickListener) {
        super(itemView);
        this.view = itemView.findViewById(R.id.image_item);
        this.mOnClickListener = mOnClickListener;
        this.context = context;
    }

    URL url = null;
    void bind(int listIndex, MovieResultsPage page) throws ExecutionException, InterruptedException {
        ImageView imageView = view.findViewById(R.id.image_item);
        imageView.setOnClickListener(this);
        MovieDb movie = page.getResults().get(listIndex);

        String path = movie.getPosterPath();
        AsyncTask<URL, Void, String> taskasdf = new BaseUrlTask().execute(url);
        String baseUrl = taskasdf.get();
        String completepath = baseUrl + "w185/" + path;
        Picasso.with(context)
                .load(completepath)
                .into(imageView);
        view.refreshDrawableState();
    }

    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mOnClickListener.onListItemClick(clickedPosition);
    }
}