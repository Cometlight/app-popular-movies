package com.scheffknecht.daniel.popularmovies.network;

import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import com.scheffknecht.daniel.popularmovies.R;
import com.scheffknecht.daniel.popularmovies.activities.MainActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by Daniel on 14.02.2017.
 */

public class FetchPopularMoviesTask extends AsyncTask<Void, Void, String> {
    private static final String MOVIEDB_MOVIE_POPULAR_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    private static final String API_KEY_PARAM = "api_key";

    private static final String MOVIEDB_API_KEY = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        URL movieRequestUrl = buildMostPopularMoviesRequestUrl();

        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);

            Log.v(TAG, "contents of request: " + jsonResponse);

            return null;
        } catch (Exception e) {
            Log.e(TAG, "doInBackground: TODO", e);
            return null;
        }
    }

    private URL buildMostPopularMoviesRequestUrl() {
        Uri uri = Uri.parse(MOVIEDB_MOVIE_POPULAR_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, MOVIEDB_API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "buildMostPopularMoviesRequestUrl: Failed to create URL from uri", e);
            return null;
        }

        Log.v(TAG, "Built URL " + url);

        return url;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
