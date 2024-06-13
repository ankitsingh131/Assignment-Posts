package com.assignment.di.component

import android.content.Context
import com.assignment.MainApplication
import com.assignment.di.module.ActivityModule
import com.assignment.di.module.ApiModule
import com.assignment.di.module.DatabaseModule
import com.assignment.di.module.FragmentModule
import com.assignment.di.module.GsonModule
import com.assignment.di.module.InterceptorModule
import com.assignment.di.module.LocalDataSourceModule
import com.assignment.di.module.NetworkModule
import com.assignment.di.module.OkHttpClientModule
import com.assignment.di.module.RemoteDataSourceModule
import com.assignment.di.module.RepositoryModule
import com.assignment.di.module.ResourceModule
import com.assignment.di.module.UseCaseModule
import com.assignment.di.module.ViewModelModule
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
    modules = [AndroidInjectionModule::class, NetworkModule::class, ApiModule::class,
        RepositoryModule::class, UseCaseModule::class, OkHttpClientModule::class,
        InterceptorModule::class, ActivityModule::class, FragmentModule::class,
        ViewModelModule::class, ResourceModule::class, GsonModule::class,
        DatabaseModule::class, LocalDataSourceModule::class, RemoteDataSourceModule::class]
)
interface AppComponent {

    fun inject(instance: MainApplication)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}