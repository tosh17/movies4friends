package ru.thstdio.feature_movies.impl.framework.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesListDto(
    @SerialName("page")
    val page: Long,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Long,

    @SerialName("results")
    val results: List<MovieDto>,
)

