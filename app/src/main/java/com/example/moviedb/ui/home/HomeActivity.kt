package com.example.moviedb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.example.moviedb.databinding.ActivityHomeBinding
import com.example.moviedb.infrastructure.BindingActivity
import com.example.moviedb.model.app.movie.Movie
import com.example.moviedb.ui.home.adapter.HomeAdapter
import com.example.moviedb.ui.home.adapter.HomeListener

class HomeActivity : BindingActivity<ActivityHomeBinding>(), HomeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindListeners()
        teste()
    }

    private fun teste() {
        val movies = listOf(
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
            Movie(
                id = 1,
                title = "The Super Mario Bros. Movie",
                overview = "Teste",
                runtime = 1,
                release_date = "2023-01-27",
                genre_ids = listOf(1),
                vote_average = 1.0,
                poster_path = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdrop_path = "Teste"
            ),
        )
        binding.rvMovieList.adapter = HomeAdapter(movies, this)
    }

    override fun onCreateBinding(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    private fun bindListeners() = with(binding) {
        ivSearch.setOnClickListener {
            tlSearch.isVisible = !tlSearch.isVisible
            hideKeyboard()
            etSearch.text?.clear()
        }

        binding.etSearch.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                getMovieByName(textView.text.toString())
            }
            true
        }
    }

    private fun getMovieByName(movieName: String) {
        //chamar função para carregar novos filmes
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    override fun onClickItem(item: Movie) {
        Log.v("teste", item.title.toString())
    }
}