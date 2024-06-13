package com.assignment.presentation.activity.prelogin

import android.os.Bundle
import com.assignment.BR
import com.assignment.R
import com.assignment.databinding.ActivityPreLoginBinding
import com.assignment.presentation.activity.base.BaseActivity
import com.assignment.presentation.fragment.login.LoginFragment
import com.assignment.presentation.viewmodel.EmptyViewModel

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.activity.prelogin
 * Project: EITC du Assignment
 **/
class PreLoginActivity : BaseActivity<ActivityPreLoginBinding, EmptyViewModel>() {
    override val classViewModel: Class<EmptyViewModel>
        get() = EmptyViewModel::class.java
    override val layoutId: Int
        get() = R.layout.activity_pre_login
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(
            R.id.container,
            LoginFragment(),
            LoginFragment::class.java.simpleName,
        ).commit()
    }
}