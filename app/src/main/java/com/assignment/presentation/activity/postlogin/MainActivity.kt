package com.assignment.presentation.activity.postlogin

import android.os.Bundle
import com.assignment.BR
import com.assignment.R
import com.assignment.databinding.ActivityMainBinding
import com.assignment.presentation.activity.base.BaseActivity
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val classViewModel: Class<MainViewModel>
        get() = MainViewModel::class.java
    override val layoutId: Int
        get() = R.layout.activity_main
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagerAdapter = PagerAdapter(this)
        getViewBinding().viewPager.adapter = pagerAdapter

        TabLayoutMediator(getViewBinding().tabLayout, getViewBinding().viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.posts)
                else -> getString(R.string.favorites)
            }
        }.attach()
    }
}