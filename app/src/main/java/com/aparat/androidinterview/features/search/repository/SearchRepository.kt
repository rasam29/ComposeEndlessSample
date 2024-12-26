package com.aparat.androidinterview.features.search.repository

import androidx.paging.PagingData
import com.aparat.androidinterview.network.model.Content
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun search(type: String, queryString: String): Flow<PagingData<Content>>
}