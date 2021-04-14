package ru.thstdio.feature_movies.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.thstdio.core_data.domain.Movies
import ru.thstdio.feature_movies.impl.domain.MoviesListCollection
import ru.thstdio.feature_movies.impl.presentation.adapters.MoviesPagingSource
import ru.thstdio.feature_movies.impl.usecase.GeTopRatedMovies
import ru.thstdio.feature_movies.impl.usecase.GetNowPlayingMovies
import ru.thstdio.feature_movies.impl.usecase.GetPopularMovies
import ru.thstdio.feature_movies.impl.usecase.GetUpcomingMovies

const val MOVIES_ON_PAGE = 20

internal class MoviesListViewModel(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val geTopRatedMovies: GeTopRatedMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getUpcomingMovies: GetUpcomingMovies
) : ViewModel() {


    private val _moviesState: MutableStateFlow<MoviesListCollection> =
        MutableStateFlow(MoviesListCollection.MoviesNowPlay)

    private val _currentMoviesList: MutableStateFlow<List<Movies>> = MutableStateFlow(listOf())
    val currentMoviesList get() = _currentMoviesList

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("MoviesListViewModel got $exception in $coroutineContext")
    }

    init {
        viewModelScope.launch(exceptionHandler) {
            //  _currentMoviesList.emit(geTopRatedMovies(1).list)
        }
    }

    fun selectListType(type: String): Flow<PagingData<Movies>> {
        return Pager(
            config = PagingConfig(pageSize = MOVIES_ON_PAGE, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviesPagingSource(
                    useCase = when (MoviesListCollection.valueOf(type)) {
                        MoviesListCollection.MoviesNowPlay -> getNowPlayingMovies
                        MoviesListCollection.MoviesPopular -> getPopularMovies
                        MoviesListCollection.MoviesTopRated -> geTopRatedMovies
                        MoviesListCollection.MoviesUpcoming -> getUpcomingMovies
                        MoviesListCollection.MoviesM4FPopular -> TODO()
                        MoviesListCollection.MoviesM4FLatest -> TODO()
                    }
                )
            }
        ).flow
    }

}
