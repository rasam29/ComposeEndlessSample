package com.aparat.androidinterview.features.detail.di

import com.aparat.androidinterview.features.detail.repository.DetailRepository
import com.aparat.androidinterview.features.detail.repository.DetailRepositoryImpl
import com.aparat.androidinterview.features.detail.service.DetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class DetailDi {
    @Provides
    @ViewModelScoped
    fun provideRepository(api: DetailApi): DetailRepository = DetailRepositoryImpl(api)
}