package com.udacity.popular_movies.detail;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.popular_movies.BuildConfig;
import com.udacity.popular_movies.Config;
import com.udacity.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.MovieDb;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_synopsis)
    TextView tv_synopsis;
    @BindView(R.id.tv_release_date)
    TextView tv_release_date;
    @BindView(R.id.tv_user_rating)
    TextView tv_user_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
            ButterKnife.bind(this);

            Intent intent = getIntent();
            if (intent == null) {
                closeOnError();
            }
            int movieId = intent.getIntExtra(Intent.EXTRA_TEXT, 0);

            new MovieTask().execute(movieId);

        } catch (Exception e) {
            Log.e("DetailActivity", e.getMessage());
            closeOnError();
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Error while loading movie data", Toast.LENGTH_SHORT).show();
    }

    private void fillActivity(MovieDb movieDb, String completePath) {
        try {
            Picasso.with(getApplicationContext())
                    .load(completePath)
                    .into(imageView);
            tv_title.setText(movieDb.getTitle());
            tv_synopsis.setText(movieDb.getOverview());
            tv_release_date.setText(movieDb.getReleaseDate());
            tv_user_rating.setText(String.valueOf(movieDb.getVoteAverage()));
        } catch (Exception e) {
            Log.e("DetailActivity", e.getMessage());
            closeOnError();
        }
    }


    class MovieTask extends AsyncTask<Integer, Void, MovieDb> {

        TmdbApi api;

        @Override
        protected MovieDb doInBackground(Integer... ids) {
            int id = ids[0];
            api = new TmdbApi(BuildConfig.TMDB_API_KEY);
            return api.getMovies().getMovie(id, Config.getLanguage());
        }

        @Override
        protected void onPostExecute(MovieDb result) {
            String completePath = api.getConfiguration().getBaseUrl() + Config.getHighResolution() + result.getBackdropPath();
            fillActivity(result, completePath);
        }
    }
}
