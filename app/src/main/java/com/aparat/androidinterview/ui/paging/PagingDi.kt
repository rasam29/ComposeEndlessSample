package com.aparat.androidinterview.ui.paging

import androidx.paging.PagingConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val DEFAULT_PAGE_SIZE = 3
private const val PREFETCH_DISTANCE = 3


@InstallIn(SingletonComponent::class)
@Module
class PagingDi {

    @Provides
    fun providePagingConfig(): PagingConfig = PagingConfig(
        pageSize = DEFAULT_PAGE_SIZE,
        prefetchDistance = PREFETCH_DISTANCE,
        initialLoadSize = DEFAULT_PAGE_SIZE
    )
}