package com.assignment.di.module

import android.content.Context
import com.assignment.presentation.utils.ResourcesResolver
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
class TestResourceModule {

    @Singleton
    @Provides
    fun provideMockResourceResolver(context: Context) = Mockito.mock(ResourcesResolver::class.java)
}