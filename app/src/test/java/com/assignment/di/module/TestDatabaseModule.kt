package com.assignment.di.module

import android.content.Context
import androidx.room.Room
import com.data.provider.local.dao.PostDao
import com.data.provider.local.db.AppDatabase
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
class TestDatabaseModule {

    @Singleton
    @Provides
    fun provideMockAppDatabase(context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @Provides
    fun provideMockPostDao(database: AppDatabase): PostDao {
        return Mockito.mock(PostDao::class.java)
    }

}