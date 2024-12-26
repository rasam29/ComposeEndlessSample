package com.aparat.androidinterview.features.home.movies.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aparat.androidinterview.features.home.movies.repository.MovieRepository
import com.aparat.androidinterview.network.model.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(movieRepository: MovieRepository) :
    ViewModel() {
    val movieListDataState: StateFlow<Flow<PagingData<Content.MovieResponse>>> =
        movieRepository.fetchMovieRepository()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
            .let { flowOf(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = flowOf(PagingData.empty())
            )
}