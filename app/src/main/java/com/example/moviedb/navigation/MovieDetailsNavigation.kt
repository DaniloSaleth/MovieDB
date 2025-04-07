package com.example.moviedb.navigation

import android.content.Context
import android.content.Intent

interface MovieDetailsNavigation {
    fun getMovieDetailsIntent(context: Context, movieId: Int): Intent

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }
}