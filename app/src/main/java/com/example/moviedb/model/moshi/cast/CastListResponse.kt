package com.example.moviedb.model.moshi.cast

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastListResponse (
    @Json(name = "id") val id: Int? = null,
    @Json(name = "cast") val cast: List<CastResponse>? = null
        )