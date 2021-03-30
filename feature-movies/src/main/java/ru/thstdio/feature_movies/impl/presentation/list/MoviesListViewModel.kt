package ru.thstdio.feature_movies.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.thstdio.core_data.domain.Movies
import ru.thstdio.feature_movies.impl.usecase.GetNowPlayingMovies

internal class MoviesListViewModel(
    getNowPlayingMovies: GetNowPlayingMovies
) : ViewModel() {

    private val _moviesState: MutableStateFlow<MoviesListViewState> =
        MutableStateFlow(MoviesListViewState.MoviesLatest)
    val moviesState get() = _moviesState

    private val _currentMoviesList: MutableStateFlow<List<Movies>> = MutableStateFlow(listOf())
    val currentMoviesList get() = _currentMoviesList

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
        println("MoviesListViewModel got $exception in $coroutineContext")
    }
    init {
        viewModelScope.launch(exceptionHandler) {
            _currentMoviesList.emit(getNowPlayingMovies(1).list)
        }
    }
}