package com.e.movie_tvshow.api;

import com.e.movie_tvshow.model.MovieArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiMovie {

    @GET("movie/popular?api_key=d87beac7a667b901ca92716168d4c0c4")
    Call<MovieArray> getMovieData();
}
