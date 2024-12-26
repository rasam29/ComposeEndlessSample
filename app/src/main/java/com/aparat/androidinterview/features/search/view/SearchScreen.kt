package com.aparat.androidinterview.features.search.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.aparat.androidinterview.R
import com.aparat.androidinterview.TYPE_MOVIE
import com.aparat.androidinterview.ui.views.AparatLazyGrid
import com.aparat.androidinterview.ui.views.AparatSearchBox
import com.aparat.androidinterview.ui.views.AparatToolbar
import com.aparat.androidinterview.ui.navigation.Screen


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    columns: GridCells = GridCells.Fixed(3),
    type: String
) {
    val searchValue = viewModel.searchFieldState.collectAsStateWithLifecycle()
    val dataState = viewModel.searchResult.collectAsStateWithLifecycle()
    val lazyPagingItems = dataState.value.collectAsLazyPagingItems()
    val gridState = rememberLazyGridState()
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        AparatToolbar(title = stringResource(id = R.string.search), hasIcon = false)
    }, bottomBar = {}) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AparatSearchBox(
                    query = searchValue.value,
                    onQueryChange = { viewModel.updateInput(it) },
                    modifier = Modifier.padding(8.dp),
                )
                AparatLazyGrid(
                    lazyPagingItems = lazyPagingItems,
                    hasEagerRequest = false,
                    state = gridState,
                    columns = columns
                ) { id -> navController.navigate(Screen.DetailScreen.route + "/$id/$TYPE_MOVIE") }
            }
        }
    }
}