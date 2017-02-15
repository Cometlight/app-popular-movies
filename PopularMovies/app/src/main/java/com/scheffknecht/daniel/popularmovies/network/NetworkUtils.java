package com.scheffknecht.daniel.popularmovies.network;

import com.scheffknecht.daniel.popularmovies.models.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Daniel on 14.02.2017.
 */

public class NetworkUtils {
    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String getImageUrl(Movie movie) {
        String imageWidth = "w185"; // possible: w92, w154, w185, w342, w500, w780, original
        String url = "http://image.tmdb.org/t/p/" + imageWidth + "/" + movie.getPosterPath();
        return url;
    }
}
