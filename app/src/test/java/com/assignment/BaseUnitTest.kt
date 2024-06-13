package com.assignment

import android.content.Context
import com.assignment.di.component.DaggerTestAppComponent
import com.assignment.di.component.TestAppComponent
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

/**
 * Author: Ankit Singh
 * Package: com.assignment
 * Project: EITC du Assignment
 **/
@RunWith(MockitoJUnitRunner::class)
open class BaseUnitTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    protected lateinit var mockWebServer: MockWebServer

    protected lateinit var testAppComponent: TestAppComponent

    protected val testScheduler = TestScheduler()

    @Before
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.trampoline() }

        testAppComponent = DaggerTestAppComponent.factory().create(
            mock(Context::class.java)
        )
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    open fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()

        mockWebServer.shutdown()
    }
}