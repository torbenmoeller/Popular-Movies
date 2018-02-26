package com.udacity.popular_movies;

import android.os.AsyncTask;

import java.net.URL;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Torben on 25.02.18.
 */

public class MovieResultPageTask extends AsyncTask<URL, Void, MovieResultsPage> {

    @Override
    protected MovieResultsPage doInBackground(URL... params) {
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        MovieResultsPage page = api.getMovies().getPopularMovies("en", 1);
        return page;
    }

}