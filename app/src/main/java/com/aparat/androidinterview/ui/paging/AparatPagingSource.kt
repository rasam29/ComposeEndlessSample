package com.aparat.androidinterview.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aparat.androidinterview.network.RemoteDataProvider

private const val MOVIE_MAX_PAGE = 500

class AparatPagingSource<T : Any>(
    private val provider: RemoteDataProvider<T>
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val result = provider.provideRemoteData(params.key ?: 1)
        return result.fold({ error -> LoadResult.Error(Throwable(error.toString())) }) {
            val nextPage =
                it.page.takeIf { page -> page != it.totalPages && page < MOVIE_MAX_PAGE }?.inc()
            LoadResult.Page(
                data = it.results, nextKey = nextPage, prevKey = null
            )
        }
    }
}