package com.data.source.post.remote

import com.data.dto.response.PostDto
import com.data.provider.remote.post.PostApi
import io.reactivex.rxjava3.core.Single

/**
 * Author: Ankit Singh
 * Package: com.data.source.post.remote
 * Project: EITC du Assignment
 **/

interface PostRemoteDataSource {
    fun getPosts(): Single<List<PostDto>>
}

class PostRemoteDataSourceImpl(private val api: PostApi): PostRemoteDataSource {
    override fun getPosts(): Single<List<PostDto>> {
        return api.getPosts()
    }
}