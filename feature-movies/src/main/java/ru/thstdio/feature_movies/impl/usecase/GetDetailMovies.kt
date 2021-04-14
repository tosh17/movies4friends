package ru.thstdio.feature_movies.impl.usecase

import ru.thstdio.core_data.domain.MoviesDetail
import ru.thstdio.feature_movies.impl.data.Repository
import javax.inject.Inject

internal class GetDetailMovies @Inject constructor(val repository: Repository) {
    suspend operator fun invoke(id: Long): MoviesDetail {
       return  repository.getMoviesDetail(id)
    }

}