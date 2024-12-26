package com.aparat.androidinterview.features.home.tvShows.view

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.aparat.androidinterview.TYPE_TV_SHOWS
import com.aparat.androidinterview.ui.views.AparatLazyGrid
import com.aparat.androidinterview.ui.navigation.Screen

@Composable
fun TVShowsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TVShowsViewModel = hiltViewModel(),
    columns: GridCells = GridCells.Fixed(3),
    gridState: LazyGridState
) {
    val dataState = viewModel.tvShowsListDataState.collectAsStateWithLifecycle()
    val lazyPagingItems = dataState.value.collectAsLazyPagingItems()
    AparatLazyGrid(
        modifier = modifier,
        columns = columns,
        state = gridState,
        lazyPagingItems = lazyPagingItems
    ) { id ->
        navController.navigate(Screen.DetailScreen.route + "/$id/$TYPE_TV_SHOWS")
    }
}