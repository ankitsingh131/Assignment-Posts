package com.assignment.di.module

import com.data.provider.local.dao.PostDao
import com.data.source.post.local.PostLocalDataSource
import com.data.source.post.local.PostLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module(includes = [DatabaseModule::class])
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun providesPostLocalDataSource(postDao: PostDao): PostLocalDataSource {
        return PostLocalDataSourceImpl(postDao)
    }
}