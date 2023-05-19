package com.example.moviedb.model.moshi.backdrops

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BackdropsResponse(
    @Json(name = "file_path") val filePath: String?,
)