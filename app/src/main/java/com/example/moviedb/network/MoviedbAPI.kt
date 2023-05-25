package com.example.moviedb.network

import com.example.moviedb.infrastructure.api.Constants.TOKEN
import com.example.moviedb.model.moshi.backdrops.BackdropsListResponse
import com.example.moviedb.model.moshi.cast.CastListResponse
import com.example.moviedb.model.moshi.genre.GenreListResponse
import com.example.moviedb.model.moshi.movie.MovieListResponse
import com.example.moviedb.model.moshi.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviedbAPI {
    @GET("movie/now_playing$TOKEN")
    suspend fun getNowPlaying(@Query("page") page: Int?): MovieListResponse

    @GET("movie/upcoming$TOKEN")
    suspend fun getUpcoming(@Query("page") pageNum: Int): MovieListResponse

    @GET("movie/popular$TOKEN")
    suspend fun getPopular(@Query("page") pageNum: Int): MovieListResponse

    @GET("genre/movie/list$TOKEN")
    suspend fun getGenres(): GenreListResponse

    @GET("movie/{movie_id}$TOKEN")
    suspend fun getMovieById(@Path("movie_id") id: Int): MovieResponse

    @GET("movie/{movie_id}/credits$TOKEN")
    suspend fun getCast(@Path("movie_id") id: Int): CastListResponse

    @GET("movie/{movie_id}/images$TOKEN")
    suspend fun getImages(@Path("movie_id") id: Int): BackdropsListResponse

    @GET("search/movie$TOKEN")
    suspend fun getMovieByName(@Query("query") movieName: String, @Query("page") pageNum: Int): MovieListResponse
}