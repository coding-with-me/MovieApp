package com.nopalyer.movieapp;

import com.nopalyer.movieapp.Model.MovieResponse;
import com.nopalyer.movieapp.Model.MovieResponse1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("movie/now_playing")
    Call<MovieResponse> getPlayingMovies(
            @Query("api_key") String apiKey
    );
    @GET("movie/upcoming")
    Call<MovieResponse1> getUpcomingMovies(
            @Query("api_key") String apiKey
    );


}
