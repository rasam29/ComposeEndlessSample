package com.aparat.androidinterview.features.home.movies.view

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.ui.navigation.Screen
import com.aparat.androidinterview.ui.views.AparatLazyGrid

@Composable
fun MovieScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
    gridState: LazyGridState,
    columns: GridCells = GridCells.Fixed(3)
) {
    val dataState = viewModel.movieListDataState.collectAsStateWithLifecycle()
    val lazyPagingItems = dataState.value.collectAsLazyPagingItems()
    AparatLazyGrid(
        modifier = modifier,
        lazyPagingItems = lazyPagingItems,
        state = gridState,
        columns = columns
    ) { id ->
        navController.navigate(Screen.DetailScreen.route + "/$id/$TYPE_MOVIE")
    }
}