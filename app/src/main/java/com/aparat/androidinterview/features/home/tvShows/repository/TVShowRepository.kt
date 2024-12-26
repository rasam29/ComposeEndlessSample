package com.aparat.androidinterview.features.home.tvShows.repository

import androidx.paging.PagingData
import com.aparat.androidinterview.network.model.Content
import kotlinx.coroutines.flow.Flow

interface TVShowRepository {
    fun getTvShows(): Flow<PagingData<Content.TVShowResponse>>
}