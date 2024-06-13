package com.assignment.di.module

import com.assignment.di.qualifier.BaseUrl
import com.data.constant.NetworkConstants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module(includes = [GsonModule::class, OkHttpClientModule::class])
class NetworkModule {

    @BaseUrl
    @Provides
    @Singleton
    fun providesBaseUrl(): String = NetworkConstants.BASE_URL

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): Factory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun providesRxJava3CallAdapterFactory(gson: Gson): CallAdapter.Factory =
        RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun providesRetrofit(
        converterFactory: Factory,
        adapterFactory: CallAdapter.Factory,
        client: OkHttpClient,
        @BaseUrl baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(adapterFactory)
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }
}