package com.assignment.presentation.activity.postlogin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.assignment.presentation.fragment.favorite.FavoritesFragment
import com.assignment.presentation.fragment.post.PostsFragment

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.activity.postlogin
 * Project: EITC du Assignment
 **/

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PostsFragment()
            else -> FavoritesFragment()
        }
    }
}