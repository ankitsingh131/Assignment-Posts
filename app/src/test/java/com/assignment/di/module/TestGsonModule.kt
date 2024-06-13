package com.assignment.di.module

import com.google.gson.Gson
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
class TestGsonModule {

    @Provides
    @Singleton
    fun providesMockGson(): Gson = Mockito.mock(Gson::class.java)
}