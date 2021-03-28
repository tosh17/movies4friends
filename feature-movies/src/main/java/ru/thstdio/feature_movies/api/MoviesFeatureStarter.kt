package ru.thstdio.feature_movies.api

import androidx.navigation.NavController
import androidx.navigation.NavGraph

interface MoviesFeatureStarter {
    fun getNavGraph(controller: NavController): NavGraph
}