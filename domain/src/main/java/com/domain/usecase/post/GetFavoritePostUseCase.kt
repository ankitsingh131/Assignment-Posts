package com.domain.usecase.post

import com.domain.entity.response.PostEntity
import com.domain.repository.post.PostRepository
import com.domain.result.ResultState
import com.domain.usecase.BaseUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.domain.usecase.post
 * Project: EITC du Assignment
 **/
class GetFavoritePostUseCase @Inject constructor(private val repository: PostRepository) :
    BaseUseCase<Void?, Single<ResultState<List<PostEntity>>>> {

    override fun execute(param: Void?): Single<ResultState<List<PostEntity>>> {
        return repository.getFavoritePosts()
    }
}