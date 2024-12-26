package com.aparat.androidinterview.features.home.movies.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import arrow.core.Either
import arrow.retrofit.adapter.either.networkhandling.CallError
import com.aparat.androidinterview.features.home.movies.repository.MovieRepository
import com.aparat.androidinterview.features.home.movies.repository.MovieRepositoryImpl
import com.aparat.androidinterview.features.home.movies.service.MovieApi
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
class MovieDI {

    @Provides
    @ViewModelScoped
    fun provideRepository(pager: Pager<Int, Content.MovieResponse>): MovieRepository =
        MovieRepositoryImpl(pager)

    @Provides
    @ViewModelScoped
    fun providePager(
        pagingConfig: PagingConfig,
        remoteDataProvider: RemoteDataProvider<Content.MovieResponse>
    ): Pager<Int, Content.MovieResponse> =
        Pager(config = pagingConfig,
            null,
            pagingSourceFactory = { AparatPagingSource(remoteDataProvider) })

    @Provides
    @ViewModelScoped
    fun provideRemoteDataProvider(service: MovieApi): RemoteDataProvider<Content.MovieResponse> =
        object : RemoteDataProvider<Content.MovieResponse> {
            override suspend fun provideRemoteData(page: Int): Either<CallError, ResponseList<Content.MovieResponse>> =
                service.getPopularMovies(page)
        }
}