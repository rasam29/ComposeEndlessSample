package com.aparat.androidinterview.features.search.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.features.search.service.SearchApi
import com.aparat.androidinterview.network.RemoteDataProvider
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.network.model.ResponseList
import com.aparat.androidinterview.ui.paging.AparatPagingSource
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val pagingConfig: PagingConfig, private val service: SearchApi) :
    SearchRepository {

    override fun search(type: String, queryString: String): Flow<PagingData<Content>> {
        return Pager(config = pagingConfig, null, pagingSourceFactory = {
            AparatPagingSource(object : RemoteDataProvider<Content> {
                override suspend fun provideRemoteData(page: Int): Either<CallError, ResponseList<out Content>> =
                    if (type == TYPE_MOVIE)
                        service.searchMovie(type, queryString, page)
                    else
                        service.searchTv(type, queryString, page)
            })
        }).flow
    }
}