package com.example.moviedb.model.app.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val release_date: String?,
    val genre_ids: List<Int>?,
    val vote_average: Double?,
    val runtime: Int?,
    val backdrop_path: String?,
) : Parcelable