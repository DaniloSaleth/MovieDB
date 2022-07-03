package com.example.citmoviedatabase.model.Movie



data class ListOfMovie(
    val page: Int,
    val results: ArrayList<Movie>,
    val total_pages: Int
)
