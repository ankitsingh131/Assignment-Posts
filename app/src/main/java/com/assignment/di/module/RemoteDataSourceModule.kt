package com.assignment.di.module

import com.data.provider.remote.post.PostApi
import com.data.source.post.remote.PostRemoteDataSource
import com.data.source.post.remote.PostRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module(includes = [ApiModule::class])
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun providesPostRemoteDataSource(postApi: PostApi): PostRemoteDataSource {
        return PostRemoteDataSourceImpl(postApi)
    }
}