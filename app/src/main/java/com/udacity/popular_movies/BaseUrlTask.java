package com.udacity.popular_movies;

import android.os.AsyncTask;

import java.net.URL;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Torben on 25.02.18.
 */

public class BaseUrlTask extends AsyncTask<URL, Void, String> {

    @Override
    protected String doInBackground(URL... params) {
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        return api.getConfiguration().getBaseUrl();
    }

}
