package com.assignment.di.module

import android.content.Context
import com.assignment.presentation.utils.ResourcesResolver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module
class ResourceModule {

    @Singleton
    @Provides
    fun provideResourceResolver(context: Context) = ResourcesResolver(context = context)
}