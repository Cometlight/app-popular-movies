package com.scheffknecht.daniel.popularmovies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.scheffknecht.daniel.popularmovies.R;
import com.scheffknecht.daniel.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import static com.scheffknecht.daniel.popularmovies.network.NetworkUtils.getImageUrl;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView moviePosterIV;
    private TextView movieTitleTV;
    private TextView movieReleaseDateTV;
    private TextView movieVoteAverageTV;
    private TextView movieSynopsisTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        moviePosterIV = (ImageView) findViewById(R.id.iv_movie_poster);
        movieTitleTV = (TextView) findViewById(R.id.tv_movie_title);
        movieReleaseDateTV = (TextView) findViewById(R.id.tv_movie_release_date);
        movieVoteAverageTV = (TextView) findViewById(R.id.tv_movie_vote_average);
        movieSynopsisTV = (TextView) findViewById(R.id.tv_movie_synopsis);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            Movie movie = intent.getParcelableExtra("movie");
            populateUsingMovie(movie);
        }
    }

    private void populateUsingMovie(Movie movie) {
        Picasso.with(this)
                .load(getImageUrl(movie))
                .fit()
                .centerCrop()
                .into(moviePosterIV);

        movieTitleTV.setText(movie.getOriginalTitle());
        movieReleaseDateTV.setText(movie.getReleaseDate());
        String avgRatingStr = getString(R.string.avg_rating)
                + ": "
                + movie.getVoteAverage().toString()
                + " / 10";
        movieVoteAverageTV.setText(avgRatingStr);
        movieSynopsisTV.setText(movie.getOverview());
    }
}
