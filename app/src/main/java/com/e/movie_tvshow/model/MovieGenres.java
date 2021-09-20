package com.e.movie_tvshow.model;

import com.google.gson.annotations.SerializedName;

public class MovieGenres {
    @SerializedName("name")
    private String genre_name;

    public MovieGenres(String genre_name) {
        this.genre_name = genre_name;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}
