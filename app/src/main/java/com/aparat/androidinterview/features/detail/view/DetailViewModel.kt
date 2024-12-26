package com.aparat.androidinterview.features.detail.view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.aparat.androidinterview.CONTENT_ID
import com.aparat.androidinterview.TYPE
import com.aparat.androidinterview.features.detail.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    detailRepository: DetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val detailContentData = savedStateHandle.getStateFlow(CONTENT_ID, -1)
        .filterNot { it == -1 }
        .combine(savedStateHandle.getStateFlow(TYPE, "")) { id, type ->
            when (val result = detailRepository.getContentDetail(id, type)) {
                is Either.Left -> UiState.Error(result.value.toString())
                is Either.Right -> UiState.Success(result.value)
            }
        }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )
}