package ru.thstdio.feature_movies.impl.data

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import ru.thstdio.core_data.domain.Genre
import ru.thstdio.feature_movies.impl.domain.MoviesListWithTotalPage
import ru.thstdio.feature_movies.impl.domain.TheMoviesDbImageConfiguration
import ru.thstdio.feature_movies.impl.framework.network.TheMovieDbApi
import ru.thstdio.module_injector.di.PerFeature
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

@PerFeature
internal class Repository @Inject constructor(
    private val api: TheMovieDbApi
) {

    private val configurationAtomic: AtomicReference<TheMoviesDbImageConfiguration> =
        AtomicReference<TheMoviesDbImageConfiguration>()
    private val genresAtomic: AtomicReference<Map<Long, Genre>> =
        AtomicReference<Map<Long, Genre>>()

    suspend fun getNowPlayingMovies(page: Int): MoviesListWithTotalPage =
        coroutineScope {
            val configurationAndGenres = async { getConfigurationAndGenres() }
            val responseAsync = async { api.getNowPlaying(page) }
            val (configuration, genres) = configurationAndGenres.await()
            val response = responseAsync.await()
            MoviesListWithTotalPage(
                list = response.results.map { movieDto ->
                    movieDto.toMovies(
                        configuration,
                        genres
                    )
                },
                totalPage = response.totalPages
            )
        }

    private suspend fun getConfigurationAndGenres(): Pair<TheMoviesDbImageConfiguration, Map<Long, Genre>> =
        coroutineScope {
            val configurationAsync = async { getConfiguration() }
            val genresAsync = async { getGenres() }
            configurationAsync.await() to genresAsync.await()
        }

    private suspend fun getConfiguration(): TheMoviesDbImageConfiguration {
        //todo сделать кеш
        val alreadyLoadedConfiguration = configurationAtomic.get()
        return if (alreadyLoadedConfiguration != null) {
            alreadyLoadedConfiguration
        } else {
            val justLoadedConfiguration = api.getConfiguration()
                .toTheMoviesDbImageConfiguration()
            configurationAtomic.set(justLoadedConfiguration)
            justLoadedConfiguration
        }
    }

    private suspend fun getGenres(): Map<Long, Genre> {
        //todo сделать кеш
        val alreadyLoadedGenres = genresAtomic.get()
        return if (alreadyLoadedGenres != null) {
            alreadyLoadedGenres
        } else {
            val genres = api.getGenresList().genres.map { genreDto -> genreDto.toGenre() }
            val justLoadedGenres: Map<Long, Genre> = genres.associateBy(Genre::id)
            genresAtomic.set(justLoadedGenres)
            justLoadedGenres
        }
    }
}