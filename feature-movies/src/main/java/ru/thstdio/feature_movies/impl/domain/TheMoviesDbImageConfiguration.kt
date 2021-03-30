package ru.thstdio.feature_movies.impl.domain

import kotlinx.serialization.SerialName

class TheMoviesDbImageConfiguration (
    val baseURL: String,
    val secureBaseURL: String,
    val backdropSizes: List<String>,
    val logoSizes: List<String>,
    val posterSizes: List<String>,
    val profileSizes: List<String>,
    val stillSizes: List<String>
)