package ru.thstdio.feature_movies.impl.domain

import ru.thstdio.core_data.domain.Movies

data class MoviesListWithTotalPage(
    val list: List<Movies>,
    val totalPage: Int
)
