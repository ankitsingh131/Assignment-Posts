package com.domain.repository.post

import com.domain.entity.response.PostEntity
import com.domain.repository.BaseRepository
import com.domain.result.ResultState
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Author: Ankit Singh
 * Package: com.domain.repository
 * Project: EITC du Assignment
 **/
interface PostRepository : BaseRepository {

    fun getPosts(): Single<ResultState<List<PostEntity>>>

    fun updateFavorite(post: PostEntity): Completable

    fun getFavoritePosts(): Single<ResultState<List<PostEntity>>>
}