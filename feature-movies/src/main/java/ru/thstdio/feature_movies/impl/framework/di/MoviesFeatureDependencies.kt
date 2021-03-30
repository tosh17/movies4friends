package ru.thstdio.feature_movies.impl.framework.di

import retrofit2.Retrofit
import ru.thstdio.module_injector.BaseDependencies

interface MoviesFeatureDependencies : BaseDependencies {
    val remotesStorage: Any
    val localStorage: Any
    val networkClient: Retrofit
}