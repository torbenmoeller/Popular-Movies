package com.udacity.popular_movies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.udacity.popular_movies.com.udacity.popular_movies.detail.DetailActivity;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.movito.themoviedbapi.model.core.MovieResultsPage;


public class MainActivity extends AppCompatActivity implements ListItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Unbinder unbinder;
    private MovieAdapter mAdapter;
    private URL url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);

            GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
            recycler_view.setLayoutManager(layoutManager);
            recycler_view.setHasFixedSize(true);
            mAdapter = new MovieAdapter(getApplicationContext(), this);
            recycler_view.setAdapter(mAdapter);
        } catch (Exception e) {
            Log.e("tag", "Error", e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onListItemClick(int movieId) {
        Intent startChildActivityIntent = new Intent(MainActivity.this, DetailActivity.class);
        startChildActivityIntent.putExtra(Intent.EXTRA_TEXT, movieId);
        startActivity(startChildActivityIntent);
    }
}