package com.assignment.di.module

import com.data.provider.remote.post.PostApi
import com.data.source.post.remote.PostRemoteDataSource
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/
@Module(includes = [TestApiModule::class])
class TestRemoteDataSourceModule {

    @Provides
    @Singleton
    fun providesMockPostRemoteDataSource(postApi: PostApi): PostRemoteDataSource {
        return Mockito.mock(PostRemoteDataSource::class.java)
    }
}