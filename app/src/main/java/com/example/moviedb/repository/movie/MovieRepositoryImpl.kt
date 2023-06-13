package com.example.moviedb.repository.movie

import com.example.moviedb.network.MoviedbAPI
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

    override fun getUpcomingMovies() = flow {
        emit(api.getUpcoming())
    }.transform {
        emit(it)
    }.catch {
        throw it
    }

    override fun getMovieByName(movieName: String) = flow {
        emit(api.getMovieByName(movieName))
    }.transform {
        emit(it)
    }.catch {
        throw it
    }
}