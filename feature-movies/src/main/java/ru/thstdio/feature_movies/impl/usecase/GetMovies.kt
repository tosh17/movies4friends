package ru.thstdio.feature_movies.impl.usecase

import ru.thstdio.feature_movies.impl.domain.MoviesListResult

interface GetMovies {
    suspend operator fun invoke(page: Int): MoviesListResult
}