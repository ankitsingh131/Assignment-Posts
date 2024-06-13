package com.assignment.di.module

import com.assignment.presentation.fragment.favorite.FavoritesFragment
import com.assignment.presentation.fragment.login.LoginFragment
import com.assignment.presentation.fragment.post.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoritesFragment(): FavoritesFragment
}