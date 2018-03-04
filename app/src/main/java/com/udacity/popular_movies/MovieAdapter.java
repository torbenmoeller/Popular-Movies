package com.udacity.popular_movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popular_movies.task.MovieResultPageTask;

import java.util.concurrent.ExecutionException;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private final ListItemClickListener mOnClickListener;
    private final SortOrder sortOrder;
    private final int PAGE_LENGTH = 20; //entries per movieResultPage

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

            Integer pageCount = position / PAGE_LENGTH + 1 ; //Which page to load, starts at offset 1
            Integer pageOffset = position % PAGE_LENGTH; //Move on page
            Pair<Integer, SortOrder> params = new Pair<>(pageCount, sortOrder);
            AsyncTask<Pair<Integer, SortOrder>, Void, MovieResultsPage> task = new MovieResultPageTask().execute(params);
            MovieResultsPage page = task.get();
            MovieDb movie = page.getResults().get(pageOffset);
            holder.bind(movie);
        } catch (ExecutionException | InterruptedException e) {
            Log.e("ViewHolder", e.getMessage());
        }
    }

}
