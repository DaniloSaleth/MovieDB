package com.example.moviedb.ui.details

import com.example.moviedb.model.movie.Movie

sealed interface DetailsState {
    data class Success(val result: Movie) : DetailsState
    object Loading : DetailsState
    object Error : DetailsState
}