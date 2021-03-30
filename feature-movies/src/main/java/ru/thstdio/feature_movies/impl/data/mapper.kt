package ru.thstdio.feature_movies.impl.data

import ru.thstdio.core_data.domain.Genre
import ru.thstdio.core_data.domain.Movies
import ru.thstdio.feature_movies.impl.domain.TheMoviesDbImageConfiguration
import ru.thstdio.feature_movies.impl.framework.network.response.ConfigurationDto
import ru.thstdio.feature_movies.impl.framework.network.response.GenreDto
import ru.thstdio.feature_movies.impl.framework.network.response.MovieDto

internal fun ConfigurationDto.toTheMoviesDbImageConfiguration(): TheMoviesDbImageConfiguration {
    return TheMoviesDbImageConfiguration(
        baseURL = this.images.baseURL,
        secureBaseURL = this.images.secureBaseURL,
        backdropSizes = this.images.backdropSizes,
        logoSizes = this.images.logoSizes,
        posterSizes = this.images.posterSizes,
        profileSizes = this.images.profileSizes,
        stillSizes = this.images.stillSizes
    )
}

internal fun GenreDto.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

internal fun MovieDto.toMovies(
    configuration: TheMoviesDbImageConfiguration,
    genresMap: Map<Long, Genre>
): Movies {
    return Movies(
        id = this.id,
        title = this.title,
        poster = createPreviewImgUrl(this.posterPath, this.backdropPath, configuration),
        genres = this.genreIDS.mapNotNull { id -> genresMap[id] },
        ratings = this.voteAverage.toFloat(),
        numberOfRatings = this.voteCount,
        adult = this.adult
    )
}

private fun createPreviewImgUrl(
    posterPath: String?, backdropPath: String?,
    configuration: TheMoviesDbImageConfiguration
): String =
    when {
        backdropPath != null -> configuration.secureBaseURL + configuration.backdropSizes[1] + backdropPath
        posterPath != null -> configuration.secureBaseURL + configuration.posterSizes[4] + posterPath
        else -> ""
    }