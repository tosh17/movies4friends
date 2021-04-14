package ru.thstdio.core_remote_db_impl.impl.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RemoteDbModule {
    @Singleton
    @Provides
    fun provideDb(): FirebaseFirestore = Firebase.firestore
}