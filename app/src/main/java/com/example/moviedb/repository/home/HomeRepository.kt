package com.example.moviedb.repository.home

import com.example.moviedb.model.movie.MovieList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getNowPlayingMovies(): Flow<MovieList>
    fun getUpcomingMovies(): Flow<MovieList>
    fun getMovieByName(movieName: String): Flow<MovieList>
}