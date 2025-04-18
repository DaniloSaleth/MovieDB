package com.example.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.example.moviedb.R
import com.example.moviedb.databinding.ActivityHomeBinding
import com.example.moviedb.infrastructure.BindingActivity
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.navigation.MovieDetailsNavigation
import com.example.moviedb.ui.home.adapter.HomeAdapter
import com.example.moviedb.ui.home.adapter.HomeListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BindingActivity<ActivityHomeBinding>(), HomeListener {

    private val viewModel: HomeViewModel by viewModel()
    private val navigation: MovieDetailsNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindListeners()
        bindObservers()
        viewModel.getNowPlayingMovies()
    }

    override fun onCreateBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    private fun bindObservers() {
        viewModel.state.observe(this) {
            setupViewState()
            when (it) {
                is HomeState.Loading -> {
                    handleLoadingState()
                }
                is HomeState.Error -> {
                    handleErrorState()
                }
                is HomeState.Success -> {
                    handleSuccessState(it.result.results)
                }
                is HomeState.EmptyState -> {
                    handleEmptyState()
                }
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        loadingState.root.isVisible = true
    }

    private fun handleErrorState() = with(binding) {
        errorState.root.isVisible = true
    }

    private fun handleSuccessState(movies: List<Movie>) = with(binding) {
        rvMovieList.isVisible = true
        rvMovieList.adapter = HomeAdapter(movies, this@HomeActivity)
    }

    private fun handleEmptyState() = with(binding) {
        emptyList.root.isVisible = true
    }

    private fun setupViewState() = with(binding) {
        rvMovieList.isVisible = false
        emptyList.root.isVisible = false
        errorState.root.isVisible = false
        loadingState.root.isVisible = false
    }

    private fun bindListeners() = with(binding) {
        ivSearch.setOnClickListener {
            mgbHome.isVisible = tlSearch.isVisible
            tlSearch.isVisible = !tlSearch.isVisible
            setupIconSearch(tlSearch.isVisible)
            hideKeyboard()
            etSearch.text?.clear()
        }

        etSearch.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                getMovieByName(textView.text.toString())
            }
            true
        }

        mgbHome.onLeftButtonClick = {
            viewModel.getNowPlayingMovies()
        }

        mgbHome.onRightButtonClick = {
            viewModel.getUpcomingMovies()
        }
    }

    private fun setupIconSearch(showSearch: Boolean) = with(binding) {
        if (showSearch) {
            ivSearch.setImageResource(R.drawable.ic_movie_home)
        } else {
            binding.ivSearch.setImageResource(R.drawable.ic_baseline_search_24)
            mgbHome.setLeftButtonSelected()
            getNowPlayingMovies()
        }
    }

    private fun getNowPlayingMovies() {
        if (viewModel.isNowPlaying.value == false) {
            viewModel.getNowPlayingMovies()
        }
    }

    private fun getMovieByName(movieName: String) {
        viewModel.getMovieByName(movieName)
    }

    private fun getMovieDetails(movieId: Int) {
        navigation.getMovieDetailsIntent(this, movieId).also {
            startActivity(it)
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onClickItem(item: Movie) {
        getMovieDetails(item.id)
    }
}