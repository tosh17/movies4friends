package ru.thstdio.movies4friends.framework.navigation

import androidx.navigation.NavController
import ru.thstdio.feature_login.api.AuthFeatureApi
import javax.inject.Inject
import javax.inject.Provider

class AppRouterImpl @Inject constructor(
    private val featureCities: Provider<AuthFeatureApi>,
) : ru.thstdio.core_data.navigation.AppRouter {
    lateinit var navController: NavController
    fun setController(navController: NavController) {
        this.navController = navController
    }
    override fun openAuth(){
        val graph = featureCities.get().getAuthFeatureStarter().getNavGraph(navController)
        navController.graph = graph
    }
}