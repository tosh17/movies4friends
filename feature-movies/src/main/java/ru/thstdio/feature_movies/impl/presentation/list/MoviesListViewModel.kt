package ru.thstdio.feature_movies.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.thstdio.core_data.domain.Movies
import ru.thstdio.feature_movies.impl.domain.MoviesListCollection
import ru.thstdio.feature_movies.impl.presentation.adapters.TitleItemView
import ru.thstdio.feature_movies.impl.usecase.GeTopRatedMovies
import ru.thstdio.feature_movies.impl.usecase.GetNowPlayingMovies
import ru.thstdio.feature_movies.impl.usecase.GetPopularMovies
import ru.thstdio.feature_movies.impl.usecase.GetUpcomingMovies

const val MOVIES_ON_PAGE = 20

internal class MoviesListViewModel(
    getNowPlayingMovies: GetNowPlayingMovies,
    geTopRatedMovies: GeTopRatedMovies,
    getPopularMovies: GetPopularMovies,
    getUpcomingMovies: GetUpcomingMovies
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

    fun selectListType(type: String) {
        viewModelScope.launch(exceptionHandler) {
            _moviesState.value = MoviesListCollection.valueOf(type)
        }
    }
}