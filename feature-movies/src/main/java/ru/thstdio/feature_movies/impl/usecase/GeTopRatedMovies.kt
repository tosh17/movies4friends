package ru.thstdio.feature_movies.impl.usecase

import ru.thstdio.feature_movies.impl.data.Repository
import ru.thstdio.feature_movies.impl.domain.MoviesListCollection
import ru.thstdio.feature_movies.impl.domain.MoviesListResult
import javax.inject.Inject

internal class GeTopRatedMovies @Inject constructor(private val repository: Repository) :GetMovies {
    override suspend operator fun invoke(page: Int): MoviesListResult {
        return repository.getMoviesListFromTheMovieDbApi(MoviesListCollection.MoviesTopRated, page)
    }

}