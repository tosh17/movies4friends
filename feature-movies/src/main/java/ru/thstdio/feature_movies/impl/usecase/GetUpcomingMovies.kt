package ru.thstdio.feature_movies.impl.usecase

import ru.thstdio.feature_movies.impl.data.Repository
import ru.thstdio.feature_movies.impl.domain.MoviesListCollection
import ru.thstdio.feature_movies.impl.domain.MoviesListResult
import javax.inject.Inject

internal class GetUpcomingMovies @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(page: Int): MoviesListResult {
        return repository.getMoviesListFromTheMovieDbApi(MoviesListCollection.MoviesUpcoming, page)
    }

}