package ru.thstdio.feature_movies.impl.framework.di

import dagger.Binds
import dagger.Module
import ru.thstdio.feature_movies.api.MoviesFeatureStarter
import ru.thstdio.feature_movies.impl.starter.MoviesFeatureStarterImpl
import ru.thstdio.module_injector.di.PerFeature

@Module
internal abstract class MoviesFeatureModule  {
    @PerFeature
    @Binds
    abstract fun provideMoviesFeatureStarter(starter: MoviesFeatureStarterImpl): MoviesFeatureStarter
}