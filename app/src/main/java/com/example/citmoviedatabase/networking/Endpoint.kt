package com.example.citmoviedatabase.networking

import com.example.citmoviedatabase.Constants.TOKEN
import com.example.citmoviedatabase.model.Cast.ListOfCast
import com.example.citmoviedatabase.model.Genre.ListOfGenres
import com.example.citmoviedatabase.model.Image.ListOfImages
import com.example.citmoviedatabase.model.Movie.ListOfMovie
import com.example.citmoviedatabase.model.Movie.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("movie/now_playing$TOKEN")
    suspend fun getNowPlaying(@Query("page") page : Int?) : ListOfMovie

    @GET("movie/upcoming$TOKEN")
    suspend fun getUpcoming(@Query("page") pageNum: Int) : ListOfMovie

    @GET("movie/popular$TOKEN")
    suspend fun getPopular(@Query("page") pageNum: Int) : ListOfMovie

    @GET("genre/movie/list$TOKEN")
    suspend fun getGenres() : ListOfGenres

    @GET("movie/{movie_id}$TOKEN")
    suspend fun getMovieById(@Path("movie_id") id: Int) : Movie

    @GET("movie/{movie_id}/credits$TOKEN")
    suspend fun getCast(@Path("movie_id") id: Int) : ListOfCast

    @GET("movie/{movie_id}/images$TOKEN")
    suspend fun getImages(@Path("movie_id") id: Int) : ListOfImages

    @GET("search/movie$TOKEN")
    suspend fun getMovieByName(@Query("query") movieName: String, @Query("page") pageNum: Int) : ListOfMovie
}