package com.scheffknecht.daniel.popularmovies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scheffknecht.daniel.popularmovies.R;
import com.scheffknecht.daniel.popularmovies.general.AsyncCallback;
import com.scheffknecht.daniel.popularmovies.models.Movie;
import com.scheffknecht.daniel.popularmovies.network.FetchPopularMoviesTask;
import com.scheffknecht.daniel.popularmovies.views.adapters.MovieAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView errorMessageDisplay;
    private ProgressBar loadingIndicator;

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        loadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        fetchMovieData();
    }

    private void fetchMovieData() {
        new FetchPopularMoviesTask(new AsyncCallback<List<Movie>>() {
            @Override
            public void onPreExecute() {
                loadingIndicator.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPostExecute(List<Movie> result) {
                loadingIndicator.setVisibility(View.INVISIBLE);
                if (result != null && result.size() > 0) {
                    movieAdapter.setMovieData(result);
                    showMovieData();
                } else {
                    showErrorMessage();
                }
            }
        }).execute();
    }

    private void showErrorMessage() {
        recyclerView.setVisibility(View.INVISIBLE);
        errorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void showMovieData() {
        recyclerView.setVisibility(View.VISIBLE);
        errorMessageDisplay.setVisibility(View.INVISIBLE);
    }
}
