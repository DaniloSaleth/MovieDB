package com.example.moviedb.repository.details

import com.example.moviedb.model.movie.Movie
import com.example.moviedb.network.MoviedbAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

class DetailsRepositoryImpl(
    private val api: MoviedbAPI
) : DetailsRepository {
    override fun getMovieDetails(movieId: Int): Flow<Movie> {
        return flow {
            emit(api.getMovieById(movieId))
        }.transform {
            emit(it)
        }.catch {
            throw it
        }
    }
}