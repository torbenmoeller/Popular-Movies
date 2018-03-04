package com.udacity.popular_movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popular_movies.com.udacity.popular_movies.SortOrder;
import com.udacity.popular_movies.com.udacity.popular_movies.task.MovieResultPageTask;

import java.util.concurrent.ExecutionException;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private final ListItemClickListener mOnClickListener;
    private final SortOrder sortOrder;

    @Override
    public int getItemCount() {
        return 100;//page.getTotalResults();
    }

    public MovieAdapter(ListItemClickListener listener, SortOrder sortOrder) {
        this.mOnClickListener = listener;
        this.sortOrder = sortOrder;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new MovieViewHolder(context, view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        try {
            Integer pageCount = position / 20 +1 ; //Which page to load, starts at 1
            Integer pageOffset = position % 20; //Move on page
            Pair<Integer, SortOrder> params = new Pair<>(pageCount, sortOrder);
            AsyncTask<Pair<Integer, SortOrder>, Void, MovieResultsPage> task = new MovieResultPageTask().execute(params);
            MovieResultsPage page = task.get();
            MovieDb movie = page.getResults().get(pageOffset);
            holder.bind(movie);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
