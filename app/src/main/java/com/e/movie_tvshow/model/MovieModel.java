package com.e.movie_tvshow.model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
    String title;
    String id;

    @SerializedName("poster_path")
    String poster_path;
    @SerializedName("vote_average")
    float vote_avarage;

    String backdrop_path;
    @SerializedName("tagline")
    String tag;

    String release_date;
    String overview;
    public MovieModel(String title, String id, String poster_path, float vote_avarage, String backdrop_path, String tag, String release_date, String overview) {
        this.title = title;
        this.id = id;
        this.poster_path = poster_path;
        this.vote_avarage = vote_avarage;
        this.backdrop_path = backdrop_path;
        this.tag = tag;
        this.release_date = release_date;
        this.overview = overview;
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public float getVote_avarage() {
        return vote_avarage;
    }

    public void setVote_avarage(float vote_avarage) {
        this.vote_avarage = vote_avarage;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
