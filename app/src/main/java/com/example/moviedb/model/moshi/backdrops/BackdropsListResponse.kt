package com.example.moviedb.model.moshi.backdrops

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BackdropsListResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "backdrops") val backdrops: List<BackdropsResponse>
)