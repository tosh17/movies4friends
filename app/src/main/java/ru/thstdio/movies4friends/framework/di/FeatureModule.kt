package ru.thstdio.movies4friends.framework.di

import dagger.Module
import dagger.Provides
import ru.thstdio.feature_login.api.AuthFeatureApi
import ru.thstdio.feature_login.impl.framework.di.AuthFeatureComponentHolder
import ru.thstdio.feature_login.impl.framework.di.AuthFeatureDependencies
import ru.thstdio.feature_movies.api.MoviesFeatureApi
import ru.thstdio.feature_movies.impl.framework.di.MoviesFeatureComponentHolder
import ru.thstdio.feature_movies.impl.framework.di.MoviesFeatureDependencies
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

    @Singleton
    @Provides
    fun provideMoviesFeatureDependencies(
    ): MoviesFeatureDependencies {
        return object : MoviesFeatureDependencies{
            override val remotesStorage: Any
                get() = TODO("Not yet implemented")
            override val localStorage: Any
                get() = TODO("Not yet implemented")
            override val networkClient: Any
                get() = TODO("Not yet implemented")
        }
    }

    @Provides
    fun provideMoviesFeature(dependencies: MoviesFeatureDependencies): MoviesFeatureApi {
        MoviesFeatureComponentHolder.init(dependencies)
        return MoviesFeatureComponentHolder.get()
    }
}