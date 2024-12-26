package com.aparat.androidinterview.features.home.movies.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.aparat.androidinterview.network.model.Content
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val pager: Pager<Int, Content.MovieResponse>) : MovieRepository {
    override fun fetchMovieRepository(): Flow<PagingData<Content.MovieResponse>> = pager.flow
}