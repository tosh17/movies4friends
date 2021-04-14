package ru.thstdio.feature_movies.impl.data

import ru.thstdio.core_data.domain.Movies
import java.util.concurrent.ConcurrentHashMap

class CacheMovies() {
    private val cache: ConcurrentHashMap<Int, List<Movies>> = ConcurrentHashMap()

    val lastPage: Int
        get() = cache.keys.maxOrNull() ?: 0
    var totalPage = 0

    fun savePage(page: Int, movies: List<Movies>) {
        cache[page] = movies
    }

    fun getPage(page: Int): List<Movies> {
        return if (cache.containsKey(page)) cache[page]!!
        else {
            error("Not found current page in cache")
        }
    }
}