package ru.thstdio.feature_movies.impl.framework.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GenresListDto(
    @SerialName("genres")
    val genres: List<GenreDto>
)

@Serializable
internal data class GenreDto(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String
)


