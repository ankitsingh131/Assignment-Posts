package com.data.provider.remote.post

import com.data.dto.response.PostDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Author: Ankit Singh
 * Package: com.data.provider.remote.post
 * Project: EITC du Assignment
 **/

interface PostApi {

    @GET("/posts")
    fun getPosts(): Single<List<PostDto>>
}