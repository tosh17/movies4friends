package ru.thstdio.core_network.api

import retrofit2.Retrofit

interface CoreNetwork {
    fun getHttpClient(): Retrofit
}