package ru.thstdio.feature_login.impl.starter

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import ru.thstdio.feature_login.R
import ru.thstdio.feature_login.api.AuthFeatureStarter
import javax.inject.Inject

class AuthFeatureStarterImpl @Inject constructor(): AuthFeatureStarter {
    override fun getNavGraph(controller: NavController): NavGraph {
        return controller.navInflater.inflate(R.navigation.auth_graph)
    }
}