package com.udacity.popular_movies.task;

import android.os.AsyncTask;

import com.udacity.popular_movies.BuildConfig;

import info.movito.themoviedbapi.TmdbApi;

public class BaseUrlTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        return api.getConfiguration().getBaseUrl();
    }
}
