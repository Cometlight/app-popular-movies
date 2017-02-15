package com.scheffknecht.daniel.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Daniel on 14.02.2017.
 */

public class Movie implements Parcelable {
    @SerializedName("poster_path")
    private String posterPath;
    private Boolean adult;
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private Float popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("vote_average")
    private Float voteAverage;

    public Movie() {}

    public Movie(String posterPath, Boolean adult, String overview, String releaseDate, Integer id, String originalTitle, String originalLanguage, String backdropPath, Float popularity, Integer voteCount, Float voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
    }

    private Movie(Parcel in) {
        posterPath = in.readString();
        adult = (Boolean) in.readValue(null);
        overview = in.readString();
        releaseDate = in.readString();
        id = in.readInt();
        originalTitle= in.readString();
        originalLanguage = in.readString();
        backdropPath = in.readString();
        popularity = in.readFloat();
        voteCount = in.readInt();
        voteAverage = in.readFloat();
    }

    public static Movie fromJson(String json) {
        return new Gson().fromJson(json, Movie.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(posterPath);
        out.writeValue(adult);
        out.writeString(overview);
        out.writeString(releaseDate);
        out.writeInt(id);
        out.writeString(originalTitle);
        out.writeString(originalLanguage);
        out.writeString(backdropPath);
        out.writeFloat(popularity);
        out.writeInt(voteCount);
        out.writeFloat(voteAverage);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }
}
