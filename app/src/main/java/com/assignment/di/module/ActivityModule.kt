package com.assignment.di.module

import com.assignment.presentation.activity.postlogin.MainActivity
import com.assignment.presentation.activity.prelogin.PreLoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.module
 * Project: EITC du Assignment
 **/

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributePreLoginActivity(): PreLoginActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}