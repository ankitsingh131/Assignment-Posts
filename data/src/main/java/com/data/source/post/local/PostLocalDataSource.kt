package com.data.source.post.local

import com.data.dto.response.PostDto
import com.data.provider.local.dao.PostDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Author: Ankit Singh
 * Package: com.data.source.post.local
 * Project: EITC du Assignment
 **/

interface PostLocalDataSource {
    fun getPosts(): Single<List<PostDto>>
    fun savePosts(posts: List<PostDto>): Completable
    fun deletePosts(): Completable
    fun updateFavorite(post: PostDto): Completable
    fun getFavoritePosts(): Single<List<PostDto>>
}

class PostLocalDataSourceImpl(private val postDao: PostDao) : PostLocalDataSource {

    override fun getPosts(): Single<List<PostDto>> {
        return postDao.allPosts()
    }

    override fun savePosts(posts: List<PostDto>): Completable {
        return postDao.insertAllPosts(posts = posts)
    }

    override fun deletePosts(): Completable {
        return postDao.deleteAllPosts()
    }

    override fun updateFavorite(post: PostDto): Completable {
        return postDao.updatePost(post)
    }

    override fun getFavoritePosts(): Single<List<PostDto>> {
        return postDao.getFavoritePosts()
    }
}