package ru.thstdio.movies4friends.framework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.thstdio.movies4friends.presentation.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [FeatureModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface MyBuilder {
        fun build(): AppComponent

        @BindsInstance
        fun setContext(applicationContext: Context): MyBuilder

    }
}