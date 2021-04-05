package ru.thstdio.feature_login.api

import ru.thstdio.module_injector.BaseAPI

interface AuthFeatureApi : BaseAPI {
    fun getAuthFeatureStarter(): AuthFeatureStarter
}