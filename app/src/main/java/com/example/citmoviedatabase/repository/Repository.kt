package com.example.citmoviedatabase.repository

import com.example.citmoviedatabase.model.Cast.Cast
import com.example.citmoviedatabase.model.Genre.Genre
import com.example.citmoviedatabase.model.Image.Backdrops
import com.example.citmoviedatabase.model.Movie.ListOfMovie
import com.example.citmoviedatabase.model.Movie.Movie

sealed class RepositoryStatus{
    data class MovieListSuccess(val response: ListOfMovie) : RepositoryStatus()
    data class MovieSuccess(val response: Movie) : RepositoryStatus()
    data class CastSuccess(val response: List<Cast>) : RepositoryStatus()
    data class ImageSuccess(val response: List<Backdrops>) : RepositoryStatus()
    data class GenreSuccess(val response: List<Genre>) : RepositoryStatus()
    data class Error(val response: Throwable) : RepositoryStatus()
}

interface Repository {
    suspend fun getGenres() : RepositoryStatus
    suspend fun getCast(movieId : Int) : RepositoryStatus
    suspend fun getImages(movieId : Int) : RepositoryStatus
    suspend fun getUpcoming(pageNum: Int): RepositoryStatus
    suspend fun getPopular(pageNum: Int): RepositoryStatus
    suspend fun getNowPlaying(page : Int) : RepositoryStatus
    suspend fun getMovieById(movieId: Int): RepositoryStatus
    suspend fun getMovieByName(movieName: String, pageNum: Int) : RepositoryStatus
}