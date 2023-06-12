package com.example.moviedb.network

import com.example.moviedb.infrastructure.api.Constants.TOKEN
import com.example.moviedb.model.movie.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviedbAPI {
    @GET("movie/now_playing$TOKEN")
    suspend fun getNowPlaying(): MovieList

    @GET("movie/upcoming$TOKEN")
    suspend fun getUpcoming() : MovieList

    @GET("search/movie$TOKEN")
    suspend fun getMovieByName(@Query("query") movieName: String) : MovieList
}