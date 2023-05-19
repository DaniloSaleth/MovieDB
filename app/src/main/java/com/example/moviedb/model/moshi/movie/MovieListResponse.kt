package com.example.moviedb.model.moshi.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val results: List<MovieResponse>?,
    @Json(name = "total_pages") val total_pages: Int?
)