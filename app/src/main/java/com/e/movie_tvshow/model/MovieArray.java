package com.e.movie_tvshow.model;

public class MovieArray {
    private MovieModel[] results;
    public MovieArray(MovieModel[] results) {
        this.results = results;
    }

    public MovieModel[] getResults() {
        return results;
    }

    public void setResults(MovieModel[] results) {
        this.results = results;
    }



}
