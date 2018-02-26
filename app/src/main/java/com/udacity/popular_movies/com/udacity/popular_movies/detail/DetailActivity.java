package com.udacity.popular_movies.com.udacity.popular_movies.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.popular_movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        String extra = intent.getStringExtra(Intent.EXTRA_TEXT);
        textView.setText(extra);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "bla", Toast.LENGTH_SHORT).show();
    }
}
