package com.aparat.androidinterview.features.home.tvShows.service

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.PAGE
import com.aparat.androidinterview.QUERY
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.network.model.ResponseList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowApi {
    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query(PAGE) page: Int
    ): Either<CallError, ResponseList<Content.TVShowResponse>>
}
