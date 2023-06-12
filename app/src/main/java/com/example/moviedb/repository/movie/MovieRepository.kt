package com.example.moviedb.repository.movie

import com.example.moviedb.model.movie.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovies(): Flow<MovieList>
    fun getUpcomingMovies(): Flow<MovieList>
    fun getMovieByName(movieName: String): Flow<MovieList>
}