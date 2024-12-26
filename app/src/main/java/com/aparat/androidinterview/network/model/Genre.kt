package com.aparat.androidinterview.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class Genre(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)

fun List<Genre>?.toConcatenatedNames():String = this?.joinToString { it.name } ?:""
