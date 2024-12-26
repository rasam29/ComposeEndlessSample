package com.aparat.androidinterview.network

import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.network.model.ResponseList

interface RemoteDataProvider<out T> {
    suspend fun provideRemoteData(page: Int): Either<CallError, ResponseList<out T>>
}