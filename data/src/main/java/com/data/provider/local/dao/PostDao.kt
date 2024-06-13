package com.data.provider.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.data.dto.response.PostDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

/**
 * Author: Ankit Singh
 * Package: com.data.provider.local.dao
 * Project: EITC du Assignment
 **/

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostDto): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(posts: List<PostDto>): Completable

    @Query("SELECT * FROM posts")
    fun allPosts(): Single<List<PostDto>>

    @Query("SELECT * FROM posts WHERE favorite = 1")
    fun getFavoritePosts(): Single<List<PostDto>>

    @Update
    fun updatePost(post: PostDto): Completable

    @Delete
    fun deletePost(vararg post: PostDto): Completable

    @Query("DELETE FROM posts WHERE id = :id")
    fun deleteAllPostsById(id: Int): Completable

    @Query("DELETE FROM posts")
    fun deleteAllPosts(): Completable
}