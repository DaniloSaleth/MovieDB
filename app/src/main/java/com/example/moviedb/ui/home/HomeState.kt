package com.example.moviedb.ui.home

import com.example.moviedb.model.movie.MovieList

sealed interface HomeState {
    data class Success(val result: MovieList) : HomeState
    object EmptyState : HomeState
    object Loading : HomeState
    object Error : HomeState
}