package ru.thstdio.feature_movies.impl.framework.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.thstdio.feature_movies.impl.framework.network.TheMovieDbApi

@Module
internal object NetworkModule {
    @Provides
    fun provideTheMovieDbApi(retrofit: Retrofit): TheMovieDbApi {
        return retrofit.create(TheMovieDbApi::class.java)
    }
}