package com.assignment.di.component

import android.content.Context
import com.assignment.di.module.TestApiModule
import com.assignment.di.module.TestDatabaseModule
import com.assignment.di.module.TestGsonModule
import com.assignment.di.module.TestLocalDataSourceModule
import com.assignment.di.module.TestNetworkModule
import com.assignment.di.module.TestOkHttpClientModule
import com.assignment.di.module.TestRemoteDataSourceModule
import com.assignment.di.module.TestRepositoryModule
import com.assignment.di.module.TestResourceModule
import com.assignment.di.module.TestUseCaseModule
import com.assignment.di.module.TestViewModelModule
import com.assignment.presentation.fragment.post.PostsViewModelTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Author: Ankit Singh
 * Package: com.assignment.di.component
 * Project: EITC du Assignment
 **/
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, TestViewModelModule::class,
        TestNetworkModule::class, TestApiModule::class, TestRepositoryModule::class,
        TestOkHttpClientModule::class, TestGsonModule::class,
        TestDatabaseModule::class, TestLocalDataSourceModule::class,
        TestRemoteDataSourceModule::class, TestResourceModule::class,
        TestUseCaseModule::class,
    ]
)
interface TestAppComponent : AppComponent {

    fun inject(postsViewModelTest: PostsViewModelTest)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestAppComponent
    }
}