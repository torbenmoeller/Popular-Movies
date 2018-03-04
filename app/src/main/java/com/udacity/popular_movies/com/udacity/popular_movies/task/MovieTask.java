package com.udacity.popular_movies.com.udacity.popular_movies.task;

import android.os.AsyncTask;

import com.udacity.popular_movies.BuildConfig;


import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;

public class MovieTask extends AsyncTask<Integer, Void, MovieDb> {

    @Override
    protected MovieDb doInBackground(Integer... ids) {
        int id = ids[0];
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        api.getConfiguration().getBaseUrl();
        return api.getMovies().getMovie(id, "en");
    }

}
