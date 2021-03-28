package ru.thstdio.feature_login.impl.framework.di

import dagger.Binds
import dagger.Module
import ru.thstdio.feature_login.api.AuthFeatureStarter
import ru.thstdio.feature_login.impl.starter.AuthFeatureStarterImpl
import ru.thstdio.module_injector.di.PerFeature

@Module
internal abstract class AuthFeatureModule {
    @PerFeature
    @Binds
    abstract fun provideAuthStarter(starter: AuthFeatureStarterImpl): AuthFeatureStarter
}