package com.aparat.androidinterview.features.search.di

import androidx.paging.PagingConfig
import com.aparat.androidinterview.features.search.repository.SearchRepository
import com.aparat.androidinterview.features.search.repository.SearchRepositoryImpl
import com.aparat.androidinterview.features.search.service.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SearchDi {
    @Provides
    @ViewModelScoped
    fun provideRepository(config: PagingConfig, api: SearchApi): SearchRepository =
        SearchRepositoryImpl(config, api)
}