package com.udacity.popular_movies.com.udacity.popular_movies.task;

import android.os.AsyncTask;

import com.udacity.popular_movies.BuildConfig;
import com.udacity.popular_movies.com.udacity.popular_movies.SortOrder;

import java.net.URL;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Torben on 25.02.18.
 */

public class MovieResultPageTask extends AsyncTask<Integer, Void, MovieResultsPage> {

    SortOrder sortOrder;
    MovieResultsPage page;

    public MovieResultPageTask(SortOrder sortOrder){
        this.sortOrder = sortOrder;
    }

    @Override
    protected MovieResultsPage doInBackground(Integer... params) {
        Integer pageNumber = params[0];
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        switch (sortOrder){
            case RatingDescending:
                page = api.getMovies().getTopRatedMovies("en", pageNumber);
                break;
            case PopularityDescending:
            default: //default, if error happened
                page = api.getMovies().getPopularMovies("en", pageNumber);
                break;
        }
        return page;
    }

}