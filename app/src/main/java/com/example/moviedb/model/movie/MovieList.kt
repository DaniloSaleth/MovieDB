package com.example.moviedb.model.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
    val page: Int?,
    val results: List<Movie>,
    val total_pages: Int?
) : Parcelable