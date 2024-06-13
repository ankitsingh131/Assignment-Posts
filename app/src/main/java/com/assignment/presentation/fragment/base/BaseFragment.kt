package com.assignment.presentation.fragment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assignment.arch.ObserverContract
import com.assignment.di.DaggerInjectable
import com.assignment.presentation.activity.base.BaseActivity
import com.assignment.presentation.viewmodel.SharedViewModel
import com.assignment.presentation.viewmodel.base.BaseViewModel
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.appberry.gita.presentation.fragment.base
 * Project: EITC du Assignment
 **/

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment(),
    DaggerInjectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val classViewModel: Class<VM>
    abstract val layoutId: Int
    abstract val bindingVariable: Int

    private lateinit var binding: VB
    protected lateinit var viewModel: VM

    private var baseActivity: BaseActivity<*, *>? = null
        private set

    open val observerContract: ObserverContract? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            baseActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[classViewModel]

        activity?.let {
            viewModel.sharedViewModel =
                ViewModelProvider(it)[SharedViewModel::class.java]
        }

        arguments?.let {
            parseArguments(arguments = it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(bindingVariable, viewModel)
        binding.executePendingBindings()

        viewModel.loadingEvent.observe(viewLifecycleOwner) {
            baseActivity?.showLoading(it)
        }

        viewModel.errorEvent.observe(viewLifecycleOwner) {
            baseActivity?.showError(it)
        }

        observerContract?.observeNetworkResponse()
        observerContract?.observeNavigationEvent()

        onViewInitialized()
    }

    override fun onResume() {
        super.onResume()

        view?.isClickable = true
        view?.isFocusableInTouchMode = true
    }

    override fun onPause() {
        super.onPause()

        view?.isClickable = false
        view?.isFocusableInTouchMode = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        observerContract?.removeObservers()
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    protected open fun parseArguments(arguments: Bundle) {
    }

    protected open fun onViewInitialized() {

    }

    protected fun getViewBinding() = binding
}