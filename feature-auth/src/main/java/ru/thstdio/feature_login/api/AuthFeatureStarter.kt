package ru.thstdio.feature_login.api

import androidx.navigation.NavController
import androidx.navigation.NavGraph

interface AuthFeatureStarter {
    fun getNavGraph(controller: NavController): NavGraph
}