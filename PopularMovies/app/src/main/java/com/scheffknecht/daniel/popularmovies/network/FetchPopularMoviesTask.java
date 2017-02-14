package com.scheffknecht.daniel.popularmovies.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by Daniel on 14.02.2017.
 */

public class FetchPopularMoviesTask extends AsyncTask<Integer, Void, String> {
    private static final String MOVIEDB_MOVIE_POPULAR_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    private static final String LANGUAGE_PARAM = "language";
    private static final String PAGE_PARAM = "page";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Integer... params) {
        if (params.length == 0) {
            Log.w(TAG, "doInBackground: no number of movies to fetch specified");
            return null;
        }
        int noMovieToFetch = params[0];

        URL movieRequestUrl = null;

        try {

        } catch (Exception e) {
            Log.e(TAG, "doInBackground: TODO", e);
            return null;
        }

        return null;
    }

    private URL buildMostPopularMoviesRequestUrl(int noMoviesToFetch) {
        Uri uri = Uri.parse(MOVIEDB_MOVIE_POPULAR_BASE_URL).buildUpon()
                .appendQueryParameter()
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
