package com.aparat.androidinterview.features.detail.service

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.CONTENT_ID
import com.aparat.androidinterview.TYPE
import com.aparat.androidinterview.network.model.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET("{$TYPE}/{$CONTENT_ID}")
    suspend fun getMovieContentDetail(
        @Path(CONTENT_ID) contentId: Int, @Path(TYPE) type: String
    ): Either<CallError, Content.MovieResponse>

    @GET("{$TYPE}/{$CONTENT_ID}")
    suspend fun getTvContentDetail(
        @Path(CONTENT_ID) contentId: Int, @Path(TYPE) type: String
    ): Either<CallError, Content.TVShowResponse>
}