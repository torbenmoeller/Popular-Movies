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

public class BaseUrlTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        return api.getConfiguration().getBaseUrl();
    }
}
