package com.aparat.androidinterview.features.home.movies.repository

import androidx.paging.PagingData
import com.aparat.androidinterview.network.model.Content
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovieRepository(): Flow<PagingData<Content.MovieResponse>>
}
