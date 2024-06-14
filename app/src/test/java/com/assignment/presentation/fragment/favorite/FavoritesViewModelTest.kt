package com.assignment.presentation.fragment.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assignment.BaseUnitTest
import com.data.constant.NetworkConstants
import com.domain.entity.ErrorEntity
import com.domain.entity.response.PostEntity
import com.domain.result.ResultState
import com.domain.usecase.post.GetFavoritePostUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.favorite
 * Project: EITC du Assignment
 **/
class FavoritesViewModelTest : BaseUnitTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var getFavoritePostUseCase: GetFavoritePostUseCase

    @Inject
    lateinit var viewModel: FavoritesViewModel

    override fun setUp() {
        super.setUp()
        testAppComponent.inject(this)
    }

    @Test
    fun `getFavoritePostList fetches and updates favorite posts`() {
        val postEntities = listOf(PostEntity("body", 1, "title", 1, true))
        `when`(getFavoritePostUseCase.execute(null)).thenReturn(
            Single.just(
                ResultState.Success(
                    postEntities
                )
            )
        )

        viewModel.getFavoritePostList()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        assert(viewModel.favoritePosts.size == postEntities.size)
        assert(viewModel.favoritePosts[0] == postEntities[0])
        assert(viewModel.favoritePosts[0].favorite)
    }

    @Test
    fun `getFavoritePostList handles error`() {
        val error = ErrorEntity(
            NetworkConstants.UNEXPECTED_ERROR_CODE,
            NetworkConstants.UNEXPECTED_ERROR_MESSAGE
        )
        `when`(getFavoritePostUseCase.execute(null)).thenReturn(Single.just(ResultState.Error(error)))

        viewModel.getFavoritePostList()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        assert(viewModel.favoritePosts.isEmpty())
    }
}