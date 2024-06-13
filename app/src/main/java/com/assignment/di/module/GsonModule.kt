package com.assignment.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module
class GsonModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setPrettyPrinting().create()
}