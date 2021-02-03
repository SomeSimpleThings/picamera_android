package com.simplethings.picamera.di

import com.apollographql.apollo.ApolloClient
import com.simplethings.picamera.BuildConfig
import com.simplethings.picamera.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideBaseUrl() = Constants.GRAPHQL_URL

    @Singleton
    @Provides
    fun gprovideApolloClient(okHttpClient: OkHttpClient, GRAPHQL_URL: String): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(GRAPHQL_URL)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }
}

