package com.assignment.di.module

import com.data.provider.remote.post.PostApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun providesPostApi(retrofit: Retrofit) = retrofit.create(PostApi::class.java)
}