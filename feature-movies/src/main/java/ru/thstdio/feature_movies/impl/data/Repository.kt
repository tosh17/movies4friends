package ru.thstdio.feature_movies.impl.data

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import ru.thstdio.core_data.domain.Genre
import ru.thstdio.feature_movies.impl.domain.MoviesListCollection
import ru.thstdio.feature_movies.impl.domain.MoviesListResult
import ru.thstdio.feature_movies.impl.domain.TheMoviesDbImageConfiguration
import ru.thstdio.feature_movies.impl.framework.network.TheMovieDbApi
import ru.thstdio.feature_movies.impl.framework.network.response.MoviesListDto
import ru.thstdio.module_injector.di.PerFeature
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

@PerFeature
internal class Repository @Inject constructor(
    private val api: TheMovieDbApi
) {
    private val moviesCollection = ConcurrentHashMap<MoviesListCollection, CacheMovies>()
    private val configurationAtomic: AtomicReference<TheMoviesDbImageConfiguration> =
        AtomicReference<TheMoviesDbImageConfiguration>()
    private val genresAtomic: AtomicReference<Map<Long, Genre>> =
        AtomicReference<Map<Long, Genre>>()


    suspend fun getMoviesListFromTheMovieDbApi(
        type: MoviesListCollection,
        page: Int
    ): MoviesListResult =
        coroutineScope {
            val moviesCache = moviesCollection.getOrPut(type, { CacheMovies() })
            if (moviesCache.lastPage >= page) {
                MoviesListResult(
                    list = moviesCache.getPage(page),
                    isLastPage = moviesCache.totalPage == page
                )
            } else {
                val configurationAndGenres = async { getConfigurationAndGenres() }
                val responseAsync = async { getListTheMovieDbApi(type, page) }
                val (configuration, genres) = configurationAndGenres.await()
                val response = responseAsync.await()
                val result = MoviesListResult(
                    list = response.results.map { movieDto ->
                        movieDto.toMovies(
                            configuration,
                            genres
                        )
                    },
                    isLastPage = response.totalPages == page
                )
                moviesCache.savePage(page, result.list)
                moviesCache.totalPage = response.totalPages
                result
            }
        }

    private suspend fun getListTheMovieDbApi(type: MoviesListCollection, page: Int): MoviesListDto {
        return when (type) {
            MoviesListCollection.MoviesNowPlay -> api.getNowPlaying(page)
            MoviesListCollection.MoviesPopular -> api.getPopular(page)
            MoviesListCollection.MoviesTopRated -> api.getTopRated(page)
            MoviesListCollection.MoviesUpcoming -> api.getUpcoming(page)
            MoviesListCollection.MoviesM4FPopular -> TODO()
            MoviesListCollection.MoviesM4FLatest -> TODO()
        }
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