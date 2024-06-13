package com.assignment.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/
@Module
class TestNetworkModule {

    @Provides
    @Singleton
    fun providesMockBaseUrl(): String = "http://localhost:8080/"

    @Provides
    @Singleton
    fun providesMockGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(Mockito.mock(Gson::class.java))

    @Provides
    @Singleton
    fun providesMockRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory =
        RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun providesMockRetrofit(
        converterFactory: GsonConverterFactory,
        adapterFactory: RxJava3CallAdapterFactory,
        client: OkHttpClient,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory)
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }
}