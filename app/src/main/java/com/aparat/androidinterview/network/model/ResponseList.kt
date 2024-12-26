package com.aparat.androidinterview.network.model

import com.squareup.moshi.Json

data class ResponseList<T>(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<T>,
    @Json(name = "total_pages") val totalPages: Int,
)
