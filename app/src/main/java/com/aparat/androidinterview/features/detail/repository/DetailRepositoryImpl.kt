package com.aparat.androidinterview.features.detail.repository

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.features.detail.service.DetailApi
import com.aparat.androidinterview.network.model.Content

class DetailRepositoryImpl(private val api: DetailApi) : DetailRepository {

    override suspend fun getContentDetail(
        contentId: Int,
        type: String
    ): Either<CallError, Content> =
        if (type == TYPE_MOVIE)
            api.getMovieContentDetail(contentId, type)
        else api.getTvContentDetail(contentId, type)
}