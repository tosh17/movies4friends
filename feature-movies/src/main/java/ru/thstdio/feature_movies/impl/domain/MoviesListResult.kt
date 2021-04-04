package ru.thstdio.feature_movies.impl.domain

import ru.thstdio.core_data.domain.Movies

data class MoviesListResult(
    val list: List<Movies>,
    val isLastPage: Boolean
)
