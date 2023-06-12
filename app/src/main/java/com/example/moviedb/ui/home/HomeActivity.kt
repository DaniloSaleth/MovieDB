package com.example.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.example.moviedb.databinding.ActivityHomeBinding
import com.example.moviedb.infrastructure.BindingActivity
import com.example.moviedb.model.movie.Movie
import com.example.moviedb.ui.home.adapter.HomeAdapter
import com.example.moviedb.ui.home.adapter.HomeListener
import com.example.moviedb.ui.home.adapter.HomeState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BindingActivity<ActivityHomeBinding>(), HomeListener {

    private val viewModel: HomeViewModel by viewModel()

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

    private fun handleLoadingState() {
        //exibir loading
    }

    private fun handleErrorState() {
        //exibir erro
    }

    private fun handleSuccessState(movies: List<Movie>) = with(binding) {
        rvMovieList.adapter = HomeAdapter(movies, this@HomeActivity)
    }

    private fun handleEmptyState() {
        //exibir lista vazia
    }

    private fun bindListeners() = with(binding) {
        ivSearch.setOnClickListener {
            tlSearch.isVisible = !tlSearch.isVisible
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

    private fun getMovieByName(movieName: String) {
        viewModel.getMovieByName(movieName)
    }

    private fun getMovieDetails(movieId: Int) {
        //chamar função para carregar detalhes do filme
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onClickItem(item: Movie) {
        getMovieDetails(item.id)
    }
}