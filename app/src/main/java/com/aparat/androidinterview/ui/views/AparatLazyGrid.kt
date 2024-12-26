package com.aparat.androidinterview.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.aparat.androidinterview.network.model.Content
import com.aparat.androidinterview.ui.paging.AparatRefreshLoadStateItem
import com.aparat.androidinterview.ui.paging.aparatAppendLoadStateItem
import com.aparat.androidinterview.ui.paging.errorLoadState

@Composable
fun <T : Content> AparatLazyGrid(
    modifier: Modifier = Modifier,
    columns: GridCells = GridCells.Fixed(3),
    lazyPagingItems: LazyPagingItems<T>,
    hasEagerRequest: Boolean = true,
    state: LazyGridState,
    onClick: (Int) -> Unit = {},
) {
    errorLoadState(lazyPagingItems.loadState, LocalContext.current)
    if (hasEagerRequest) {
        AparatRefreshLoadStateItem(lazyPagingItems.loadState)
    }
    LazyVerticalGrid(columns = columns, modifier = modifier.fillMaxSize(), state = state) {
        items(lazyPagingItems.itemCount) { index ->
            lazyPagingItems[index]?.let {
                AparatContentItem(content = it, onClick = { id ->
                    onClick.invoke(id)
                })
            }
        }
        aparatAppendLoadStateItem(lazyPagingItems.loadState)
    }
}