package com.assignment.di.module

import com.data.repository.post.PostRepositoryImpl
import com.data.source.post.local.PostLocalDataSource
import com.data.source.post.remote.PostRemoteDataSource
import com.domain.repository.post.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module(includes = [ApiModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun providesPostRepository(
        localDataSource: PostLocalDataSource,
        remoteDataSource: PostRemoteDataSource,
    ): PostRepository {
        return PostRepositoryImpl(localDataSource, remoteDataSource)
    }
}