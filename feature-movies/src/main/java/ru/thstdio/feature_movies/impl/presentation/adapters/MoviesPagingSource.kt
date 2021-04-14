package ru.thstdio.feature_movies.impl.presentation.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.thstdio.core_data.domain.Movies
import ru.thstdio.feature_movies.impl.usecase.GetMovies

private const val START_PAGE = 1

class MoviesPagingSource<T : GetMovies>(private val useCase: T) : PagingSource<Int, Movies>() {
    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        val page = params.key ?: START_PAGE
        val result = useCase(page)
        val nextPage = if (result.isLastPage) null else page + 1
        return LoadResult.Page(
            data = result.list,
            prevKey = if (page == START_PAGE) null else page - 1,
            nextKey = nextPage
        )
    }
}