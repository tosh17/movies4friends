package ru.thstdio.core_data.domain

data class MovieDetail(
    val id: Long,
    val title: String,
    val overview: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)
