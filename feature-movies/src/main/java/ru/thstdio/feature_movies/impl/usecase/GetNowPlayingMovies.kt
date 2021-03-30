package ru.thstdio.feature_movies.impl.usecase

import ru.thstdio.feature_movies.impl.data.Repository
import ru.thstdio.feature_movies.impl.domain.MoviesListWithTotalPage
import javax.inject.Inject

internal class GetNowPlayingMovies @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(page: Int): MoviesListWithTotalPage {
        return repository.getNowPlayingMovies(page)
    }

}