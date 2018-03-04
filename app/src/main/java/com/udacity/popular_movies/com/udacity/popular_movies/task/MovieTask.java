package com.udacity.popular_movies.com.udacity.popular_movies.task;

import android.os.AsyncTask;

import com.udacity.popular_movies.BuildConfig;


import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by Torben on 25.02.18.
 */
public class MovieTask extends AsyncTask<Integer, Void, MovieDb> {

    @Override
    protected MovieDb doInBackground(Integer... ids) {
        int id = ids[0];
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        api.getConfiguration().getBaseUrl();
        MovieDb movie = api.getMovies().getMovie(id, "en");
        return movie;
    }

}
