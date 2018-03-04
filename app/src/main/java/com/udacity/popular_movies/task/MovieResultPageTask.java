package com.udacity.popular_movies.task;

import android.os.AsyncTask;
import android.util.Pair;

import com.udacity.popular_movies.BuildConfig;
import com.udacity.popular_movies.Config;
import com.udacity.popular_movies.SortOrder;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

public class MovieResultPageTask extends AsyncTask<Pair<Integer, SortOrder>, Void, MovieResultsPage> {

    @Override
    protected MovieResultsPage doInBackground(Pair<Integer, SortOrder>... params) {
        Integer pageNumber = params[0].first;
        SortOrder sortOrder = params[0].second;
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        switch (sortOrder){
            case RatingDescending:
                return api.getMovies().getTopRatedMovies(Config.getLanguage(), pageNumber);
            case PopularityDescending:
            default: //default, if error happened
                return api.getMovies().getPopularMovies(Config.getLanguage(), pageNumber);
        }
    }

}