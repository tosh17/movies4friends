package ru.thstdio.movies4friends.framework.di

import dagger.Module
import dagger.Provides
import ru.thstdio.feature_login.api.AuthFeatureApi
import ru.thstdio.feature_login.impl.framework.di.AuthFeatureComponentHolder
import ru.thstdio.feature_login.impl.framework.di.AuthFeatureDependencies
import javax.inject.Singleton

@Module
object FeatureModule {

    @Singleton
    @Provides
    fun provideAuthFeatureDependencies(
   ): AuthFeatureDependencies {
        return object : AuthFeatureDependencies{
            override val remotesStorage: Any
                get() = TODO("Not yet implemented")
            override val localStorage: Any
                get() = TODO("Not yet implemented")
        }
    }

    @Provides
    fun provideAuthFeature(dependencies: AuthFeatureDependencies): AuthFeatureApi {
        AuthFeatureComponentHolder.init(dependencies)
        return AuthFeatureComponentHolder.get()
    }
}