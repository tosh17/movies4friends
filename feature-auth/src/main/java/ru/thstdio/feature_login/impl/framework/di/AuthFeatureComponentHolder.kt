package ru.thstdio.feature_login.impl.framework.di

import android.util.Log
import ru.thstdio.feature_login.api.AuthFeatureApi
import ru.thstdio.module_injector.ComponentHolder

object AuthFeatureComponentHolder  : ComponentHolder<AuthFeatureApi, AuthFeatureDependencies> {
    private var component: AuthFeatureComponent? = null
    private const val TAG = "AuthFeature"

    override fun init(dependencies: AuthFeatureDependencies) {
        Log.i(TAG, "init()")
        if (component == null) {
            synchronized(AuthFeatureComponentHolder::class.java) {
                if (component == null) {
                    Log.i(TAG, "initAndGet()")
                    component = AuthFeatureComponent.initAndGet(dependencies)
                }
            }
        }
    }

    override fun get(): AuthFeatureApi = getComponent()

    internal fun getComponent(): AuthFeatureComponent {
        checkNotNull(component) { "AuthFeatureComponentHolder was not initialized!" }
        return component!!
    }

    override fun reset() {
        Log.i(TAG, "reset()")
        component = null
    }
}