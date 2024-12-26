package com.aparat.androidinterview.features.detail.repository

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.network.model.Content

interface DetailRepository {
    suspend fun getContentDetail(
        contentId: Int,
        type: String
    ): Either<CallError, Content>
}