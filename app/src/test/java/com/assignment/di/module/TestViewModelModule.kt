package com.assignment.di.module

import com.assignment.presentation.activity.postlogin.MainViewModel
import com.assignment.presentation.fragment.favorite.FavoritesViewModel
import com.assignment.presentation.fragment.login.LoginViewModel
import com.assignment.presentation.fragment.post.PostsViewModel
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
class TestViewModelModule {

    @Provides
    fun provideLoginViewModel(): LoginViewModel = LoginViewModel()

    @Provides
    fun provideMainViewModel(): MainViewModel = MainViewModel()

    @Provides
    fun providePostsViewModel(
        getPostUseCase: GetPostUseCase,
        updateFavoriteUseCase: UpdateFavoriteUseCase
    ): PostsViewModel = PostsViewModel(
        getPostUseCase = getPostUseCase,
        updateFavoriteUseCase = updateFavoriteUseCase
    )

    @Provides
    fun provideFavoritesViewModel(getFavoritePostUseCase: GetFavoritePostUseCase): FavoritesViewModel =
        FavoritesViewModel(getFavoritePostUseCase = getFavoritePostUseCase)
}
