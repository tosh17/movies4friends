package ru.thstdio.feature_movies.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.thstdio.feature_movies.impl.usecase.GeTopRatedMovies
import ru.thstdio.feature_movies.impl.usecase.GetNowPlayingMovies
import ru.thstdio.feature_movies.impl.usecase.GetPopularMovies
import ru.thstdio.feature_movies.impl.usecase.GetUpcomingMovies
import javax.inject.Inject

internal class MoviesListViewModelFactory @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val geTopRatedMovies: GeTopRatedMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getUpcomingMovies: GetUpcomingMovies
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MoviesListViewModel::class.java -> MoviesListViewModel(
                getNowPlayingMovies,
                geTopRatedMovies,
                getPopularMovies,
                getUpcomingMovies
            )
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T

    }

}