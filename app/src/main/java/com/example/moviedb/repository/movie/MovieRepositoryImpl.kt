package com.example.moviedb.repository.movie

import com.example.moviedb.model.movie.MovieList
import com.example.moviedb.network.MoviedbAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

class MovieRepositoryImpl(
    private val api: MoviedbAPI
) : MovieRepository {
    override fun getNowPlayingMovies() = flow {
        emit(api.getNowPlaying())
    }.transform {
        emit(it)
    }.catch {
        throw it
    }

    override fun getUpcomingMovies(): Flow<MovieList> {
        return flow {
            emit(api.getUpcoming())
        }.transform {
            emit(it)
        }.catch {
            throw it
        }
    }

    override fun getMovieByName(movieName: String): Flow<MovieList> {
        return flow {
            emit(api.getMovieByName(movieName))
        }.transform {
            emit(it)
        }.catch {
            throw it
        }
    }
}