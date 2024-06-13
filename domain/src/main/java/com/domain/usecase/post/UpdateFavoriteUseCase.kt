package com.domain.usecase.post

import com.domain.entity.response.PostEntity
import com.domain.repository.post.PostRepository
import com.domain.usecase.BaseUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.domain.usecase.post
 * Project: EITC du Assignment
 **/
class UpdateFavoriteUseCase @Inject constructor(private val repository: PostRepository) :
    BaseUseCase<PostEntity, Completable> {

    override fun execute(param: PostEntity): Completable {
        return repository.updateFavorite(param)
    }
}