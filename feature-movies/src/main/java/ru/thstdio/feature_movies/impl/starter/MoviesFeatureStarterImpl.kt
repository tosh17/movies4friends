package ru.thstdio.feature_movies.impl.starter

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import ru.thstdio.feature_movies.R
import ru.thstdio.feature_movies.api.MoviesFeatureStarter
import javax.inject.Inject

class MoviesFeatureStarterImpl  @Inject constructor() : MoviesFeatureStarter {
    override fun getNavGraph(controller: NavController): NavGraph {
        return controller.navInflater.inflate(R.navigation.movie_graph)
    }
}