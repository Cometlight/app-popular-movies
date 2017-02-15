package com.scheffknecht.daniel.popularmovies.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static com.scheffknecht.daniel.popularmovies.network.NetworkUtils.getImageUrl;

/**
 * Created by Daniel on 14.02.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    private final MovieAdapterOnClickHandler onClickHandler;

    private List<Movie> movies = new ArrayList<>(0);

    public MovieAdapter(MovieAdapterOnClickHandler onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

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

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    /**
     * @param movies The new movies to be displayed.
     */
    public void setMovieData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;

        private final TextView movieTextView;
        private final ImageView movieThumbnailImageView;

        private Movie movieBound;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            this.context = itemView.getContext();
            movieTextView = (TextView) itemView.findViewById(R.id.tv_movie_item_title);
            movieThumbnailImageView = (ImageView) itemView.findViewById(R.id.iv_movie_item_thumbnail);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            movieBound = movie;
            movieTextView.setText(movieBound.getOriginalTitle());

            Picasso.with(context)
                .load(getImageUrl(movieBound))
                .fit()
                .centerCrop()
                .into(movieThumbnailImageView);
        }

        @Override
        public void onClick(View view) {
            onClickHandler.onClick(movieBound);
        }
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
