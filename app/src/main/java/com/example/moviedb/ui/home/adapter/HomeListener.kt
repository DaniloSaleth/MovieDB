package com.example.moviedb.ui.home.adapter

import com.example.moviedb.model.movie.Movie

interface HomeListener {
    fun onClickItem(item: Movie)
}