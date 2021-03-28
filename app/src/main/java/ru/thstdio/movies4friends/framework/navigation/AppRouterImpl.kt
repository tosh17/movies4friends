package ru.thstdio.movies4friends.framework.navigation

import androidx.navigation.NavController
import ru.thstdio.feature_login.api.AuthFeatureApi
import ru.thstdio.feature_movies.api.MoviesFeatureApi
import javax.inject.Inject
import javax.inject.Provider

class AppRouterImpl @Inject constructor(
    private val authFeature: Provider<AuthFeatureApi>,
    private val moviesFeature :Provider<MoviesFeatureApi>,
) : ru.thstdio.core_data.navigation.AppRouter {
    lateinit var navController: NavController
    fun setController(navController: NavController) {
        this.navController = navController
    }
    override fun openAuth(){
        val graph = authFeature.get().getAuthFeatureStarter().getNavGraph(navController)
        navController.graph = graph
    }

    override fun startMainScreen() {
        val graph = moviesFeature.get().getMoviesFeatureStarter().getNavGraph(navController)
        navController.graph = graph
    }

}