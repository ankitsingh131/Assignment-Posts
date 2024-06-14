package com.assignment.presentation.fragment.favorite

import androidx.databinding.ObservableArrayList
import com.assignment.BR
import com.assignment.R
import com.assignment.presentation.viewmodel.base.BaseViewModel
import com.domain.entity.response.PostEntity
import com.domain.usecase.post.GetFavoritePostUseCase
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.favorite
 * Project: EITC du Assignment
 **/
class FavoritesViewModel @Inject constructor(private val getFavoritePostUseCase: GetFavoritePostUseCase) :
    BaseViewModel() {

    val favoritePosts: ObservableArrayList<PostEntity> = ObservableArrayList()
    val itemBinding =
        ItemBinding.of<PostEntity>(BR.item, R.layout.item_post)

    fun getFavoritePostList() {
        getFavoritePostUseCase.execute(null).subscribe {  result ->
            showLoading(false)
            result.process(
                onSuccess = {
                    favoritePosts.clear()
                    favoritePosts.addAll(it)
                }
            )
        }.track()
    }
}