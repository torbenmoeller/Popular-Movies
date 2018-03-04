package com.udacity.popular_movies.com.udacity.popular_movies.task;

import android.os.AsyncTask;
import android.util.Pair;

import com.udacity.popular_movies.BuildConfig;
import com.udacity.popular_movies.com.udacity.popular_movies.SortOrder;

import java.net.URL;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Torben on 25.02.18.
 */

public class MovieResultPageTask extends AsyncTask<Pair<Integer, SortOrder>, Void, MovieResultsPage> {

    @Override
    protected MovieResultsPage doInBackground(Pair<Integer, SortOrder>... params) {
        Integer pageNumber = params[0].first;
        SortOrder sortOrder = params[0].second;
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        switch (sortOrder){
            case RatingDescending:
                return api.getMovies().getTopRatedMovies("en", pageNumber);
            case PopularityDescending:
            default: //default, if error happened
                return api.getMovies().getPopularMovies("en", pageNumber);
        }
    }

}