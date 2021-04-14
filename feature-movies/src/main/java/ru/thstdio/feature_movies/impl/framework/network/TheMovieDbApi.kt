package ru.thstdio.feature_movies.impl.framework.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.thstdio.feature_movies.impl.framework.network.response.*
import ru.thstdio.feature_movies.impl.framework.network.response.ConfigurationDto
import ru.thstdio.feature_movies.impl.framework.network.response.GenresListDto
import ru.thstdio.feature_movies.impl.framework.network.response.MoviesListDto

internal interface TheMovieDbApi {
    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationDto

    @GET("genre/movie/list")
    suspend fun getGenresList(@Query("language") language: String = "ru"): GenresListDto

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int,
        @Query("language") language: String = "ru"
    ): MoviesListDto

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
        @Query("language") language: String = "ru"
    ): MoviesListDto

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int,
        @Query("language") language: String = "ru"
    ): MoviesListDto

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int,
        @Query("language") language: String = "ru"
    ): MoviesListDto

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Long,
        @Query("language") language: String = "ru"
    ): MovieDetailDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Long,
        @Query("language") language: String = "ru"
    ): MovieCreditsDto
}