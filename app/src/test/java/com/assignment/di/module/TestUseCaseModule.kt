package com.assignment.di.module

import com.domain.repository.post.PostRepository
import com.domain.usecase.post.GetFavoritePostUseCase
import com.domain.usecase.post.GetPostUseCase
import com.domain.usecase.post.UpdateFavoriteUseCase
import dagger.Module
import dagger.Provides

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/
@Module
class TestUseCaseModule {

    @Provides
    fun provideGetPostUseCase(
        repository: PostRepository,
    ): GetPostUseCase = GetPostUseCase(
        repository = repository
    )

    @Provides
    fun provideGetFavoritePostUseCase(
        repository: PostRepository,
    ): GetFavoritePostUseCase = GetFavoritePostUseCase(
        repository = repository
    )

    @Provides
    fun provideUpdateFavoriteUseCase(
        repository: PostRepository,
    ): UpdateFavoriteUseCase = UpdateFavoriteUseCase(
        repository = repository
    )
}