package ru.thstdio.core_data.domain

data class Movies(
    val id: Long,
    val title: String,
    val poster: String,
    val genres: List<Genre>,
    val ratings: Float,
    val numberOfRatings: Int,
    val adult: Boolean
)
