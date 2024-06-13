package com.assignment.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/
@Module
class TestOkHttpClientModule {

    @Provides
    @Singleton
    fun providesMockOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Mockito.mock(HttpLoggingInterceptor::class.java))
            .build()
    }
}