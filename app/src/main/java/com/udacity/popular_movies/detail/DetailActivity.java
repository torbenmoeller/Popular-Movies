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
import com.udacity.popular_movies.Config;
import com.udacity.popular_movies.R;
import com.udacity.popular_movies.task.MovieTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.movito.themoviedbapi.model.MovieDb;
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title) TextView tv_title;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.tv_synopsis) TextView tv_synopsis;
    @BindView(R.id.tv_release_date) TextView tv_release_date;
    @BindView(R.id.tv_user_rating) TextView tv_user_rating;

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

            AsyncTask<Integer, Void, MovieDb> task = new MovieTask().execute(movieId);
            MovieDb page = task.get();
            String completePath = Config.getBaseUrl() + Config.getHighResolution() + page.getBackdropPath();

            Picasso.with(getApplicationContext())
                    .load(completePath)
                    .into(imageView);
            tv_title.setText(page.getTitle());
            tv_synopsis.setText(page.getOverview());
            tv_release_date.setText(page.getReleaseDate());
            tv_user_rating.setText(String.valueOf(page.getVoteAverage()));

        }catch (Exception e) {
            Log.e("DetailActivity", e.getMessage());
            closeOnError();
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Error while loading movie data", Toast.LENGTH_SHORT).show();
    }
}
