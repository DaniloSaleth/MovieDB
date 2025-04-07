package com.example.moviedb.navigation

import android.content.Context
import android.content.Intent
import com.example.moviedb.ui.details.DetailsActivity

class MovieDetailsNavigationImpl : MovieDetailsNavigation {
    override fun getMovieDetailsIntent(context: Context, movieId: Int): Intent {
        return Intent(context, DetailsActivity::class.java).apply {
            putExtra(MovieDetailsNavigation.MOVIE_ID, movieId)
        }
    }
}