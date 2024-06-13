package com.assignment.di.module

import com.data.source.post.local.PostLocalDataSource
import com.data.source.post.remote.PostRemoteDataSource
import com.domain.repository.post.PostRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/
@Module(includes = [TestLocalDataSourceModule::class, TestRemoteDataSourceModule::class])
class TestRepositoryModule {

    @Provides
    @Singleton
    fun providesMockPostRepository(
        localDataSource: PostLocalDataSource,
        remoteDataSource: PostRemoteDataSource,
    ): PostRepository {
        return Mockito.mock(PostRepository::class.java)
    }
}