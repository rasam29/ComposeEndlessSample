package com.aparat.androidinterview.features.home.tvShows.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.aparat.androidinterview.network.model.Content
import kotlinx.coroutines.flow.Flow

class TVShowRepositoryImpl(private val pager: Pager<Int, Content.TVShowResponse>) :
    TVShowRepository {
    override fun getTvShows(): Flow<PagingData<Content.TVShowResponse>> = pager.flow
}