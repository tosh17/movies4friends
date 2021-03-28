package ru.thstdio.feature_movies.api

import ru.thstdio.module_injector.BaseAPI


interface MoviesFeatureApi : BaseAPI {
    fun getMoviesFeatureStarter(): MoviesFeatureStarter
}