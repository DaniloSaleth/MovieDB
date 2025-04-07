package com.example.moviedb.repository.details

import com.example.moviedb.model.movie.Movie
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun getMovieDetails(movieId: Int): Flow<Movie>
}