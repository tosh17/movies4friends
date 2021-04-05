package ru.thstdio.movies4friends.presentation

import android.app.Application
import ru.thstdio.movies4friends.framework.di.AppComponent
import ru.thstdio.movies4friends.framework.di.DaggerAppComponent

class M4FApp : Application() {
    companion object {
        lateinit var daggerComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.builder().setContext(applicationContext).build()
    }
}