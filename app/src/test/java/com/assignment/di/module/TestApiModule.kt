package com.assignment.di.module

import com.data.provider.remote.post.PostApi
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/
@Module
class TestApiModule {

    @Provides
    @Singleton
    fun providesMockPostApi(): PostApi = Mockito.mock(PostApi::class.java)
}