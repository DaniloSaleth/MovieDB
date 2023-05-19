package com.example.moviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.example.moviedb.databinding.ActivityHomeBinding
import com.example.moviedb.infrastructure.BindingActivity

internal class HomeActivity : BindingActivity<ActivityHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindListeners()
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
}