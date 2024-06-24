package com.assignment.presentation.activity.base

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.assignment.R
import com.assignment.arch.ObserverContract
import com.assignment.presentation.control.loader.ProgressHandler
import com.assignment.presentation.utils.LocaleHelper
import com.assignment.presentation.utils.safeGet
import com.assignment.presentation.viewmodel.SharedViewModel
import com.assignment.presentation.viewmodel.base.BaseViewModel
import com.domain.entity.ErrorEntity
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.activity.base
 * Project: EITC du Assignment
 **/

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    abstract val classViewModel: Class<VM>
    abstract val layoutId: Int
    abstract val bindingVariable: Int

    private lateinit var binding: VB
    protected lateinit var viewModel: VM

    private lateinit var progressHandler: ProgressHandler

    open val observerContract: ObserverContract? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        LocaleHelper.getCurrentLanguage(this)?.let { setUpLocale(it, false) }
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory)[classViewModel]

        viewModel.sharedViewModel =
            ViewModelProvider(this)[SharedViewModel::class.java]

        binding.setVariable(bindingVariable, viewModel)
        binding.executePendingBindings()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressHandler = ProgressHandler(this, false)
    }

    override fun onStart() {
        super.onStart()

        viewModel.loadingEvent.observe(this) {
            showLoading(it)
        }

        viewModel.errorEvent.observe(this) {
            showError(it)
        }

        observerContract?.observeNetworkResponse()
        observerContract?.observeNavigationEvent()
    }

    fun isLoaderShowing(): Boolean = progressHandler.isShowing()

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && isLoaderShowing()) {
            if (progressHandler.isCancelable()) {
                progressHandler.hide()
            }
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    fun showLoading(it: Boolean?) {
        it?.let { disableTouch ->
            if (disableTouch) {
                progressHandler.show()
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            } else {
                progressHandler.hide()
                window.clearFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            }
        }
    }

    fun showError(error: ErrorEntity?) {
        error?.let {
            Snackbar.make(getViewBinding().root, it.errorMessage.safeGet(), Snackbar.LENGTH_SHORT).show()
        }
    }

    fun setUpLocale(lngCode: String, shouldNotify: Boolean = true): Context? {
        val currentLanguage = LocaleHelper.getCurrentLanguage(this)
        if (!currentLanguage.equals(lngCode, true)) {
            val newLocaleContext = LocaleHelper.setNewLocale(this, lngCode)
            if (shouldNotify) {
                LocaleHelper.notifyLanguageChange(this)
            } else {
                return newLocaleContext
            }
        }
        return null
    }

    override fun onStop() {
        super.onStop()

        viewModel.loadingEvent.removeObservers(this)
        viewModel.errorEvent.removeObservers(this)

        observerContract?.removeObservers()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.setLocale(base))
    }

    protected fun getViewBinding() = binding

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}