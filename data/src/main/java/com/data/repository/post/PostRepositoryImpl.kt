package com.data.repository.post

import com.data.mapper.dtotoentity.map
import com.data.mapper.entitytodto.toDto
import com.data.repository.BaseRepositoryImpl
import com.data.source.post.local.PostLocalDataSource
import com.data.source.post.remote.PostRemoteDataSource
import com.domain.entity.response.PostEntity
import com.domain.repository.post.PostRepository
import com.domain.result.ResultState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Author: Ankit Singh
 * Package: com.data.repository.post
 * Project: EITC du Assignment
 **/
class PostRepositoryImpl(
    private val localDataSource: PostLocalDataSource,
    private val remoteDataSource: PostRemoteDataSource,
) : PostRepository, BaseRepositoryImpl() {

    override fun getPosts(): Single<ResultState<List<PostEntity>>> {
        return remoteDataSource.getPosts()
            .flatMap { remotePosts ->
                localDataSource.savePosts(remotePosts)
                    .andThen(Single.just(remotePosts))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<ResultState<List<PostEntity>>> { postDtos ->
                ResultState.Success(postDtos.map())
            }
            .onErrorResumeNext { error ->
                localDataSource.getPosts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { localPosts ->
                        if (localPosts.isNotEmpty()) {
                            ResultState.SuccessWithLocalData(localPosts.map(), handleError(error))
                        } else {
                            ResultState.Error(handleError(error))
                        }
                    }
                    .onErrorReturn {
                        ResultState.Error(handleError(error))
                    }
            }
    }

    override fun updateFavorite(post: PostEntity): Completable {
        return localDataSource.updateFavorite(post.toDto())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFavoritePosts(): Single<ResultState<List<PostEntity>>> {
        return localDataSource.getFavoritePosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<ResultState<List<PostEntity>>> { postDtos ->
                ResultState.Success(postDtos.map())
            }
            .onErrorReturn {
                ResultState.Error(handleError(it))
            }
    }
}