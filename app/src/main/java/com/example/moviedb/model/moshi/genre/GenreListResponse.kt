package com.example.moviedb.model.moshi.genre

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreListResponse(
    @Json(name = "genres") val genres: List<GenreResponse>?
)