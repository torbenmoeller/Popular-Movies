package com.udacity.popular_movies;

import android.os.AsyncTask;

import java.net.URL;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by Torben on 25.02.18.
 */
public class MovieTask extends AsyncTask<URL, Void, MovieDb> {

  @Override
    protected MovieDb doInBackground(URL... params) {
        TmdbApi api = new TmdbApi(BuildConfig.TMDB_API_KEY);
        api.getConfiguration().getBaseUrl();
        MovieResultsPage page = api.getMovies().getPopularMovies("en", 1);
        MovieDb movie1 = page.getResults().get(0);
        return movie1;
    }

}
