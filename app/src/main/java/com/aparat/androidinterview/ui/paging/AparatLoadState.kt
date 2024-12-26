package com.aparat.androidinterview.ui.paging

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.aparat.androidinterview.R
import com.aparat.androidinterview.ui.views.AparatCircularLoading


fun errorLoadState(loadState: CombinedLoadStates, context: Context) {
    if (loadState.refresh is LoadState.Error || loadState.append is LoadState.Error) {
        Toast.makeText(context, context.getString(R.string.error_fetching_data), Toast.LENGTH_LONG)
            .show()
    }
}

@Composable
fun AparatRefreshLoadStateItem(loadState: CombinedLoadStates) {
    if (loadState.refresh is LoadState.Loading && loadState.append is LoadState.NotLoading) {
        AparatCircularLoading()
    }
}

fun LazyGridScope.aparatAppendLoadStateItem(loadState: CombinedLoadStates) {
    if (loadState.append is LoadState.Loading) {
        item {
            AparatCircularLoading(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp))
        }
    }
}