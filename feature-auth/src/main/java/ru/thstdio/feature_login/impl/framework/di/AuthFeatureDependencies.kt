package ru.thstdio.feature_login.impl.framework.di

import ru.thstdio.module_injector.BaseDependencies

interface AuthFeatureDependencies :BaseDependencies {
    val remotesStorage : Any
    val localStorage : Any
}