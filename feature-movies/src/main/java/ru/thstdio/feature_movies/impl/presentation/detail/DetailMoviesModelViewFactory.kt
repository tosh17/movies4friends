package ru.thstdio.feature_movies.impl.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.thstdio.feature_movies.impl.usecase.GetDetailMovies
import javax.inject.Inject

internal class DetailMoviesModelViewFactory @Inject constructor(
    private val getDetailMovies: GetDetailMovies
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            DetailMoviesModelView::class.java -> DetailMoviesModelView(
                getDetailMovies
            )
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T

    }
}