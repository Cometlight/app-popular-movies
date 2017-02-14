package com.scheffknecht.daniel.popularmovies.activities;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scheffknecht.daniel.popularmovies.R;
import com.scheffknecht.daniel.popularmovies.network.FetchPopularMoviesTask;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMovieData();
    }

    private void loadMovieData() {
        new FetchPopularMoviesTask().execute();
    }
}
