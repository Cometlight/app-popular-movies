package com.scheffknecht.daniel.popularmovies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scheffknecht.daniel.popularmovies.R;
import com.scheffknecht.daniel.popularmovies.general.AsyncCallback;
import com.scheffknecht.daniel.popularmovies.models.Movie;
import com.scheffknecht.daniel.popularmovies.network.FetchPopularMoviesTask;
import com.scheffknecht.daniel.popularmovies.views.adapters.MovieAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieOverviewActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {
    private TextView errorMessageDisplay;
    private ProgressBar loadingIndicator;
    private MenuItem menuItemSortingSpinner;
    private Spinner sortingSpinner;

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    private boolean isShowingMovieData = false;

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

        movieAdapter = new MovieAdapter(this);
        movieAdapter.setHasStableIds(true);
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
        isShowingMovieData = false;
        recyclerView.setVisibility(View.INVISIBLE);
        errorMessageDisplay.setVisibility(View.VISIBLE);
        if (menuItemSortingSpinner != null)
            menuItemSortingSpinner.setVisible(false);
    }

    private void showMovieData() {
        isShowingMovieData = true;
        recyclerView.setVisibility(View.VISIBLE);
        errorMessageDisplay.setVisibility(View.INVISIBLE);
        if (menuItemSortingSpinner != null)
            menuItemSortingSpinner.setVisible(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overview, menu);

        menuItemSortingSpinner = menu.findItem(R.id.spinner_sort);
        menuItemSortingSpinner.setVisible(isShowingMovieData);
        sortingSpinner = (Spinner) menuItemSortingSpinner.getActionView();
        initSortingSpinner();

        return true;
    }

    private void initSortingSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_dropdown_item);
        sortingSpinner.setAdapter(adapter);
        sortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<Movie> movies = movieAdapter.getMovies();
                if (position == 0) /* sort by popularity */ {
                    movies = sortByPopularity(movies);
                } else /* sort by rating */ {
                    movies = sortByRating(movies);
                }
                movieAdapter.setMovieData(movies);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onClick(Movie movie) {
        Intent movieDetailIntent = new Intent(this, MovieDetailActivity.class);
        movieDetailIntent.putExtra("movie", movie);
        startActivity(movieDetailIntent);
    }

    private List<Movie> sortByPopularity(List<Movie> movies) {
        // movies.sort() available only starting with api level 24
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m2.getPopularity().compareTo(m1.getPopularity());
            }
        });
        return movies;
    }

    private List<Movie> sortByRating(List<Movie> movies) {
        // movies.sort() available only starting with api level 24
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m2.getVoteAverage().compareTo(m1.getVoteAverage());
            }
        });
        return movies;
    }
}
