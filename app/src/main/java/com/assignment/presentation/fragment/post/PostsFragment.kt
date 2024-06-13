package com.assignment.presentation.fragment.post

import com.assignment.BR
import com.assignment.R
import com.assignment.arch.ObserverContract
import com.assignment.databinding.PostsFragmentBinding
import com.assignment.presentation.fragment.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.post
 * Project: EITC du Assignment
 **/
class PostsFragment : BaseFragment<PostsFragmentBinding, PostsViewModel>(), ObserverContract {
    override val classViewModel: Class<PostsViewModel>
        get() = PostsViewModel::class.java
    override val layoutId: Int
        get() = R.layout.posts_fragment
    override val bindingVariable: Int
        get() = BR.viewModel
    override val observerContract: ObserverContract
        get() = this

    override fun onViewInitialized() {
        super.onViewInitialized()

        viewModel.getPostList()
    }

    override fun observeNavigationEvent() {
        super.observeNavigationEvent()

        viewModel.updateFavoriteEvent.observe(viewLifecycleOwner) {
            val message =
                if (it.favorite) getString(R.string.post_has_been_marked_as_favorite) else "Post has been removed from favorite."
            Snackbar.make(getViewBinding().recyclerViewPosts, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}