package com.assignment.presentation.fragment.post

import androidx.databinding.ObservableArrayList
import com.assignment.BR
import com.assignment.R
import com.assignment.arch.SingleLiveEvent
import com.assignment.presentation.listener.OnItemClickListener
import com.assignment.presentation.viewmodel.base.BaseViewModel
import com.domain.entity.response.PostEntity
import com.domain.usecase.post.GetPostUseCase
import com.domain.usecase.post.UpdateFavoriteUseCase
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.post
 * Project: EITC du Assignment
 **/
class PostsViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
) : BaseViewModel() {

    val posts: ObservableArrayList<PostEntity> = ObservableArrayList()
    val itemBinding =
        ItemBinding.of<PostEntity>(BR.item, R.layout.item_post)
            .bindExtra(BR.itemClickListener, object : OnItemClickListener<PostEntity> {
                override fun onItemClicked(item: PostEntity) {
                    updateFavorite(item)
                }
            })


    val updateFavoriteEvent = SingleLiveEvent<PostEntity>()

    fun getPostList() {
        showLoading(true)
        getPostUseCase.execute(null).subscribe { result ->
            showLoading(false)
            result.process(
                onSuccess = {
                    posts.clear()
                    posts.addAll(it)
                }
            )
        }.track()
    }

    fun updateFavorite(post: PostEntity) {
        post.favorite = !post.favorite
        showLoading(true)
        updateFavoriteUseCase.execute(post).subscribe({
            showLoading(false)
            updateFavoriteEvent.value = post
        }, {
            showLoading(false)
        }).track()
    }
}