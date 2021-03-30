package ru.thstdio.feature_movies.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.thstdio.feature_movies.impl.usecase.GetNowPlayingMovies
import javax.inject.Inject

internal class MoviesListViewModelFactory @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MoviesListViewModel::class.java -> MoviesListViewModel(
                getNowPlayingMovies
            )
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T

    }

}