package com.scheffknecht.daniel.popularmovies.network;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.scheffknecht.daniel.popularmovies.general.AsyncCallback;
import com.scheffknecht.daniel.popularmovies.models.Movie;

import static android.content.ContentValues.TAG;

/**
 * Created by Daniel on 14.02.2017.
 *
 * Fetches and returns the most popular movies from moviedb.
 * If the optional AsyncCallback is supplied via the constructor, its methods are called accordingly.
 */
public class FetchPopularMoviesTask extends AsyncTask<Void, Void, List<Movie>> {
    private static final String MOVIEDB_MOVIE_POPULAR_BASE_URL = "https://api.themoviedb.org/3/movie/popular";
    private static final String API_KEY_PARAM = "api_key";

    private static final String MOVIEDB_API_KEY = "";

    private AsyncCallback<List<Movie>> asyncCallback;

    public FetchPopularMoviesTask() {}

    public FetchPopularMoviesTask(AsyncCallback<List<Movie>> asyncCallback) {
        this.asyncCallback = asyncCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (asyncCallback != null) {
            asyncCallback.onPreExecute();
        }
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        URL movieRequestUrl = buildMostPopularMoviesRequestUrl();

        List<Movie> movies = null;
        try {
            String jsonResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
            movies = getMoviesFromJsonResponse(jsonResponse);
        } catch (Exception e) {
            Log.e(TAG, "doInBackground: Failed to fetch movies", e);
        }
        return movies;
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

        return url;
    }

    /**
     * jsonResponse should look like this:
     *   {
     *     "page": 1,
     *     "results": [
     *       {
     *         "poster_path": ...
     *         ...
     * @param jsonResponse the JSON from a get-popular-movies request
     */
    private List<Movie> getMoviesFromJsonResponse(String jsonResponse) {
        JsonElement jsonElement = new JsonParser().parse(jsonResponse);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray results = jsonObject.getAsJsonArray("results");
        Gson gson = new Gson();

        Type datasetListType = new TypeToken<Collection<Movie>>() {}.getType();
        List<Movie> movies = gson.fromJson(results, datasetListType);

        return movies;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        if (asyncCallback != null) {
            asyncCallback.onPostExecute(movies);
        }
    }
}
