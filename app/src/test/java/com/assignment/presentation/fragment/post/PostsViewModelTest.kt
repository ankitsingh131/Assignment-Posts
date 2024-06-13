package com.assignment.presentation.fragment.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assignment.BaseUnitTest
import com.domain.entity.response.PostEntity
import com.domain.result.ResultState
import com.domain.usecase.post.GetPostUseCase
import com.domain.usecase.post.UpdateFavoriteUseCase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.post
 * Project: EITC du Assignment
 **/
class PostsViewModelTest : BaseUnitTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var getPostUseCase: GetPostUseCase

    @Inject
    lateinit var updateFavoriteUseCase: UpdateFavoriteUseCase

    @Inject
    lateinit var viewModel: PostsViewModel

    override fun setUp() {
        super.setUp()
        testAppComponent.inject(this)
    }

    @Test
    fun getPostList() {
        val postEntities = listOf(PostEntity("body", 1, "title", 1))
        val mockResponse = MockResponse()
            .setBody("[{\"body\":\"body\", \"id\":1, \"title\":\"title\", \"userId\":1}]")
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)

        `when`(getPostUseCase.execute(null)).thenReturn(Single.just(ResultState.Success(postEntities)))

        viewModel.getPostList()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        assert(viewModel.posts.size == postEntities.size)
        assert(viewModel.posts[0] == postEntities[0])
    }

    @Test
    fun updateFavorite() {
        val postEntity = PostEntity("body", 1, "title", 1, false)

        `when`(updateFavoriteUseCase.execute(postEntity)).thenReturn(Completable.complete())

        viewModel.updateFavoriteEvent.observeForever {
            assert(it.favorite)
        }

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        viewModel.updateFavorite(postEntity)

        Assert.assertFalse(viewModel.loadingEvent.value!!)
    }
}