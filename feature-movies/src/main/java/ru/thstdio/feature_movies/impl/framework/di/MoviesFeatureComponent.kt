package ru.thstdio.feature_movies.impl.framework.di

import dagger.Component
import ru.thstdio.feature_movies.api.MoviesFeatureApi
import ru.thstdio.feature_movies.impl.presentation.list.MoviesListFragment
import ru.thstdio.module_injector.di.PerFeature

@Component(
    modules = [MoviesFeatureModule::class],
    dependencies = [MoviesFeatureDependencies::class]
)
@PerFeature
abstract class MoviesFeatureComponent : MoviesFeatureApi {
    abstract fun inject(fragment: MoviesListFragment)

    companion object {
        fun initAndGet(dependencies: MoviesFeatureDependencies): MoviesFeatureComponent {
            return DaggerMoviesFeatureComponent.builder()
                .moviesFeatureDependencies(dependencies)
                .build()
        }
    }
}