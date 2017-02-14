package com.scheffknecht.daniel.popularmovies.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scheffknecht.daniel.popularmovies.R;
import com.scheffknecht.daniel.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 14.02.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private List<Movie> movies = new ArrayList<>(0);

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.movie_list_item, parent, false);
        MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    /**
     * @param movies The new movies to be displayed.
     */
    public void setMovieData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        private final TextView movieTextView;
        private final ImageView movieThumbnailImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            movieTextView = (TextView) itemView.findViewById(R.id.tv_movie_title);
            movieThumbnailImageView = (ImageView) itemView.findViewById(R.id.iv_movie_thumbnail);
        }

        public void bind(Movie movie) {
            movieTextView.setText(movie.getOriginalTitle());

            Picasso.with(context)
                .load(getImageUrl(movie))
                .fit()
                .centerCrop()
                .into(movieThumbnailImageView);
        }

        private String getImageUrl(Movie movie) {
            String imageWidth = "w185"; // possible: w92, w154, w185, w342, w500, w780, original
            String url = "http://image.tmdb.org/t/p/" + imageWidth + "/" + movie.getPosterPath();
            return url;
        }
    }
}
