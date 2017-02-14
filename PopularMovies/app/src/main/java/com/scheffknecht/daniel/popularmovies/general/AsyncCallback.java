package com.scheffknecht.daniel.popularmovies.general;

/**
 * Created by Daniel on 14.02.2017.
 */

public interface AsyncCallback<T> {
    void onPreExecute();
    void onPostExecute(T result);
}
