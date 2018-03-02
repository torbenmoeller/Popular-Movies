package com.udacity.popular_movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popular_movies.com.udacity.popular_movies.SortOrder;
import com.udacity.popular_movies.com.udacity.popular_movies.task.MovieResultPageTask;

import java.util.concurrent.ExecutionException;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private Context context;
    private ListItemClickListener mOnClickListener;
    private SortOrder sortOrder;

    @Override
    public int getItemCount() {
        return 100;//page.getTotalResults();
    }

    public MovieAdapter(Context context, ListItemClickListener listener, SortOrder sortOrder) {
        this.context = context;
        this.mOnClickListener = listener;
        this.sortOrder = sortOrder;
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
            Integer pageCount = position / 20 +1 ; //Which page to load, starts at 1
            Integer pageOffset = position % 20; //Move on page
            AsyncTask<Integer, Void, MovieResultsPage> task = new MovieResultPageTask(sortOrder).execute(pageCount);
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
