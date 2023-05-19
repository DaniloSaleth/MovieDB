package com.example.moviedb.model.moshi.cast

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "character") val character: String? = null,
    @Json(name = "profile_path") val profile_path: String? = null,
)