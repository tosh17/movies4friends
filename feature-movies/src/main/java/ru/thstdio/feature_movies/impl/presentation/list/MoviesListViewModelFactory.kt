package ru.thstdio.feature_movies.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MoviesListViewModelFactory @Inject constructor(
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MoviesListViewModel::class.java -> MoviesListViewModel(
            )
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T

    }

}