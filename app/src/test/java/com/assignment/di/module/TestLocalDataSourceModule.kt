package com.assignment.di.module

import com.data.provider.local.dao.PostDao
import com.data.source.post.local.PostLocalDataSource
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/
@Module(includes = [TestDatabaseModule::class])
class TestLocalDataSourceModule {

    @Provides
    @Singleton
    fun providesMockPostLocalDataSource(postDao: PostDao): PostLocalDataSource {
        return Mockito.mock(PostLocalDataSource::class.java)
    }
}