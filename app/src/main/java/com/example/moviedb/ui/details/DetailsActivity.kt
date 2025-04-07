package com.example.moviedb.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.moviedb.databinding.ActivityDetailsBinding
import com.example.moviedb.infrastructure.BindingActivity
import com.example.moviedb.infrastructure.api.Constants.POSTER_PATH
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.navigation.MovieDetailsNavigation.Companion.MOVIE_ID
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : BindingActivity<ActivityDetailsBinding>() {
    private val movieId: Int by lazy { intent.getIntExtra(MOVIE_ID, 0) }
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovieDetails(movieId)
        bindObservers()
    }

    override fun onCreateBinding(layoutInflater: LayoutInflater): ActivityDetailsBinding {
        return ActivityDetailsBinding.inflate(layoutInflater)
    }

    private fun bindObservers() {
        viewModel.state.observe(this) {
            setupViewState()
            when (it) {
                is DetailsState.Loading -> {
                    handleLoadingState()
                }
                is DetailsState.Error -> {
                    handleErrorState()
                }
                is DetailsState.Success -> {
                    handleSuccessState(it.result)
                }
            }
        }
    }

    private fun handleSuccessState(movie: Movie) = with(binding) {
        successState.isVisible = true
        tvMovieTitle.text = movie.title
        tvMovieRate.text = movie.vote_average.toString()
        tvMovieDuration.text = movie.runtime.toString() + " min"
        Glide.with(ivMovieBanner).load(POSTER_PATH + movie.backdrop_path).into(ivMovieBanner)
    }

    private fun setupViewState() = with(binding) {
        errorState.root.isVisible = false
        loadingState.root.isVisible = false
        successState.isVisible = false
    }

    private fun handleErrorState() = with(binding) {
        errorState.root.isVisible = true
    }

    private fun handleLoadingState() = with(binding) {
        loadingState.root.isVisible = true
    }
}