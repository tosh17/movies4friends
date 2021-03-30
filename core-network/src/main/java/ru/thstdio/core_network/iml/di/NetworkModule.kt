package ru.thstdio.core_network.iml.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ru.thstdio.core_network.BuildConfig
import ru.thstdio.core_network.iml.data.interceptor.ApiHeaderInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    fun provideClient(interceptor: ApiHeaderInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideParamInterceptor(): ApiHeaderInterceptor {
        return ApiHeaderInterceptor(
            apiKey = BuildConfig.THE_MOVIES_DB_API_KEY
        )
    }

    @ExperimentalSerializationApi
    @Provides
    fun provideJsonConverterFactory(): Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, converter: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.THE_MOVIES_DB_BASE_URL)
            .addConverterFactory(converter)
            .build()
    }

}