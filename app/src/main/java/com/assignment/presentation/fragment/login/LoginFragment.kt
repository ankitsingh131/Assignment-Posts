package com.assignment.presentation.fragment.login

import android.app.LocaleManager
import android.content.Intent
import com.assignment.BR
import com.assignment.R
import com.assignment.arch.ObserverContract
import com.assignment.databinding.LoginFragmentBinding
import com.assignment.presentation.activity.postlogin.MainActivity
import com.assignment.presentation.constant.Constants
import com.assignment.presentation.fragment.base.BaseFragment
import com.assignment.presentation.utils.LocaleHelper

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.login
 * Project: EITC du Assignment
 **/

class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>(), ObserverContract {
    override val classViewModel: Class<LoginViewModel>
        get() = LoginViewModel::class.java
    override val layoutId: Int
        get() = R.layout.login_fragment
    override val bindingVariable: Int
        get() = BR.viewModel
    override val observerContract: ObserverContract
        get() = this

    override fun observeNavigationEvent() {
        super.observeNavigationEvent()

        viewModel.loginSuccessEvent.observe(viewLifecycleOwner) {
            val intent = Intent(requireActivity(), MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }

        viewModel.changeLanguageEvent.observe(viewLifecycleOwner) {
            if (LocaleHelper.getCurrentLanguage(context) == Constants.LANGUAGE_ENGLISH) {
                setAppLocale(Constants.LANGUAGE_ARABIC)
            } else {
                setAppLocale(Constants.LANGUAGE_ENGLISH)
            }
        }
    }
}