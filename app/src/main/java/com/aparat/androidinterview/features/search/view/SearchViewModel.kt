package com.aparat.androidinterview.features.search.view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aparat.androidinterview.TYPE
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.features.search.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    searchRepository: SearchRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _searchFieldState: MutableStateFlow<String> = MutableStateFlow("")
    val searchFieldState: StateFlow<String> = _searchFieldState.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResult =
        _searchFieldState.debounce(300).filter { it.trim().isNotBlank() }.filter { it.length > 2 }
            .flatMapLatest { queryString ->
                searchRepository.search(
                    savedStateHandle[TYPE] ?: TYPE_MOVIE,
                    queryString
                )
            }.cachedIn(viewModelScope).let { flowOf(it) }.flowOn(Dispatchers.IO).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyFlow()
            )

    fun updateInput(query: String) {
        _searchFieldState.value = query
    }
}


