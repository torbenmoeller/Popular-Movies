package com.udacity.popular_movies.com.udacity.popular_movies.task;

import android.os.AsyncTask;

import com.udacity.popular_movies.BuildConfig;

import java.net.URL;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Torben on 25.02.18.
 */

public class MovieResultPageTask extends AsyncTask<Integer, Void, MovieResultsPage> {

    @Override
    protected MovieResultsPage doInBackground(Integer... params) {
        Integer pageNumber = params[0];
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        MovieResultsPage page = api.getMovies().getPopularMovies("en", pageNumber);
        return page;
    }

}