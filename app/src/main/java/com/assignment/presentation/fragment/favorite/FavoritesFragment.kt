package com.assignment.presentation.fragment.favorite

import com.assignment.BR
import com.assignment.R
import com.assignment.arch.ObserverContract
import com.assignment.databinding.FavoritesFragmentBinding
import com.assignment.presentation.fragment.base.BaseFragment

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.favorite
 * Project: EITC du Assignment
 **/
class FavoritesFragment: BaseFragment<FavoritesFragmentBinding, FavoritesViewModel>(),
    ObserverContract {
    override val classViewModel: Class<FavoritesViewModel>
        get() = FavoritesViewModel::class.java
    override val layoutId: Int
        get() = R.layout.favorites_fragment
    override val bindingVariable: Int
        get() = BR.viewModel
    override val observerContract: ObserverContract
        get() = this

    override fun observeNavigationEvent() {
        super.observeNavigationEvent()

        viewModel.sharedViewModel.fetchFavoritePosts.observe(viewLifecycleOwner) {
            viewModel.getFavoritePostList()
        }
    }
}