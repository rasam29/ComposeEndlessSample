package com.aparat.androidinterview.features.search.service

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.PAGE
import com.aparat.androidinterview.QUERY
import com.aparat.androidinterview.TYPE
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.network.model.ResponseList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("search/{$TYPE}")
    suspend fun searchTv(
        @Path(TYPE) type: String,
        @Query(QUERY) query: String,
        @Query(PAGE) page: Int
    ): Either<CallError, ResponseList<Content.TVShowResponse>>

    @GET("search/{$TYPE}")
    suspend fun searchMovie(
        @Path(TYPE) type: String,
        @Query(QUERY) query: String,
        @Query(PAGE) page: Int
    ): Either<CallError, ResponseList<Content.MovieResponse>>
}