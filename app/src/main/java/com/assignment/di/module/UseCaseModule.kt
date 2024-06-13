package com.assignment.di.module

import dagger.Module

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

//    @Provides
//    @Singleton
//    fun providesChapterUseCase(chapterRepository: PostRepository): GetPostUseCase {
//        return ChapterUseCaseImpl(chapterRepository)
//    }
}