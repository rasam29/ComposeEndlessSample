package com.aparat.androidinterview.features.home.tvShows.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.features.home.tvShows.repository.TVShowRepository
import com.aparat.androidinterview.features.home.tvShows.repository.TVShowRepositoryImpl
import com.aparat.androidinterview.features.home.tvShows.service.TVShowApi
import com.aparat.androidinterview.network.RemoteDataProvider
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.network.model.ResponseList
import com.aparat.androidinterview.ui.paging.AparatPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class TVShowsDI {

    @Provides
    @ViewModelScoped
    fun provideTVShowsRepository(pager: Pager<Int, Content.TVShowResponse>): TVShowRepository =
        TVShowRepositoryImpl(pager)

    @Provides
    @ViewModelScoped
    fun providePagerForTVShows(
        pagingConfig: PagingConfig,
        remoteDataProvider: RemoteDataProvider<Content.TVShowResponse>
    ): Pager<Int, Content.TVShowResponse> = Pager(
        config = pagingConfig,
        initialKey = null,
        pagingSourceFactory = { AparatPagingSource(remoteDataProvider) })

    @Provides
    @ViewModelScoped
    fun provideRemoteProvider(service: TVShowApi): RemoteDataProvider<Content.TVShowResponse> =
        object : RemoteDataProvider<Content.TVShowResponse> {
            override suspend fun provideRemoteData(page: Int): Either<CallError, ResponseList<Content.TVShowResponse>> =
                service.getPopularTvShows(page)
        }
}