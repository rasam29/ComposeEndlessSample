package com.aparat.androidinterview.network.model

import androidx.compose.runtime.Immutable
import com.aparat.androidinterview.POSTER_BASE_URL
import com.aparat.androidinterview.THUMBNAIL_BASE_URL
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.TYPE_TV_SHOWS
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Immutable
sealed interface Content {
    val type: String
    val id: Int
    val title: String
    val date: String
    val thumbnail: String?
    val voteAverage: Float
    val genres: List<Genre>?

    @JsonClass(generateAdapter = false)
    @Immutable
    data class MovieResponse(
        @Json(name = "id") override val id: Int,
        @Json(name = "title") override val title: String,
        @Json(name = "release_date") override val date: String,
        @Json(name = "poster_path") override val thumbnail: String?,
        @Json(name = "vote_average") override val voteAverage: Float,
        @Json(name = "genres") override val genres: List<Genre>?,
        override val type: String = TYPE_MOVIE,
    ) : Content

    @JsonClass(generateAdapter = false)
    @Immutable
    data class TVShowResponse(
        @Json(name = "id") override val id: Int,
        @Json(name = "name") override val title: String,
        @Json(name = "first_air_date") override val date: String,
        @Json(name = "poster_path") override val thumbnail: String?,
        @Json(name = "vote_average") override val voteAverage: Float,
        @Json(name = "genres") override val genres: List<Genre>?,
        override val type: String = TYPE_TV_SHOWS
    ) : Content
}

fun Content.parenthesisedVoteAverage(): String {
    return "(${voteAverage})"
}

fun Content.completeThumbnailUrl(): String = THUMBNAIL_BASE_URL + this.thumbnail

fun Content.completePoster(): String = POSTER_BASE_URL + this.thumbnail
