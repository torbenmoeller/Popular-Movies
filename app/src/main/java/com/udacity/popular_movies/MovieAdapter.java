package com.udacity.popular_movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.ExecutionException;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private MovieResultsPage page;
    private Context context;
    private ListItemClickListener mOnClickListener;

    @Override
    public int getItemCount() {
        return page.getTotalResults();
    }

    public MovieAdapter(MovieResultsPage page, Context context, ListItemClickListener listener) {
        this.page = page;
        this.context = context;
        this.mOnClickListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        MovieViewHolder viewHolder = new MovieViewHolder(context, view, mOnClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        try {

            MovieDb movie = page.getResults().get(position);
            holder.bind(movie);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
