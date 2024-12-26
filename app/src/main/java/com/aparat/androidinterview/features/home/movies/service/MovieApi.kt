package com.aparat.androidinterview.features.home.movies.service

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.PAGE
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.network.model.ResponseList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(PAGE) page: Int
    ): Either<CallError, ResponseList<Content.MovieResponse>>
}
