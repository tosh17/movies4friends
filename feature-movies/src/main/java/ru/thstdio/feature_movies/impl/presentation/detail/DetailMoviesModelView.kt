package ru.thstdio.feature_movies.impl.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.thstdio.core_data.domain.MoviesDetail
import ru.thstdio.feature_movies.impl.usecase.GetDetailMovies

internal class DetailMoviesModelView(
    private val getDetailMovies: GetDetailMovies
) : ViewModel() {

    private val _moviesDetail: MutableStateFlow<MoviesDetail?> = MutableStateFlow(null)
    val moviesDetail: StateFlow<MoviesDetail?> get() = _moviesDetail.asStateFlow()

    fun updateMovie(moviesId: Long) {
        viewModelScope.launch {
            _moviesDetail.emit(getDetailMovies(moviesId))
        }
    }


}