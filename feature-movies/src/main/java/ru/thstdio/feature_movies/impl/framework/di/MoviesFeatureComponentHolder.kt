package ru.thstdio.feature_movies.impl.framework.di

import android.util.Log
import ru.thstdio.feature_movies.api.MoviesFeatureApi
import ru.thstdio.module_injector.ComponentHolder

object MoviesFeatureComponentHolder  : ComponentHolder<MoviesFeatureApi,MoviesFeatureDependencies> {
    private var component:  MoviesFeatureComponent? = null
    private const val TAG = "MoviesFeature"

    override fun init(dependencies: MoviesFeatureDependencies) {
        Log.i(TAG, "init()")
        if (component == null) {
            synchronized(MoviesFeatureComponentHolder::class.java) {
                if (component == null) {
                    Log.i(TAG, "initAndGet()")
                    component =  MoviesFeatureComponent.initAndGet(dependencies)
                }
            }
        }
    }

    override fun get(): MoviesFeatureApi = getComponent()

    internal fun getComponent():  MoviesFeatureComponent {
        checkNotNull(component) { "MoviesFeatureComponentHolder was not initialized!" }
        return component!!
    }

    override fun reset() {
        Log.i(TAG, "reset()")
        component = null
    }
}