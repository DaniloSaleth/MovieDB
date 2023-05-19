package com.example.moviedb.model.moshi.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse (
    @Json(name = "id") val id: Int,
    @Json(name = "overview") val overview: String?,
    @Json(name = "poster_path") val poster_path: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "release_date") val release_date: String?,
    @Json(name = "genre_ids") val genre_ids: List<Int>?,
    @Json(name = "vote_average") val vote_average: Double?,
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "backdrop_path") val backdrop_path: String?,
)