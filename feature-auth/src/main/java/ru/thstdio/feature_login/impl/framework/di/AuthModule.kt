package ru.thstdio.feature_login.impl.framework.di

import dagger.Module
import dagger.Provides
import ru.thstdio.core_auth.firebase.AppAuth

@Module
object AuthModule {
    @Provides
    fun provideAuth(): AppAuth = AppAuth()
}