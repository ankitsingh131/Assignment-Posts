package com.assignment.presentation.viewmodel.base

import androidx.lifecycle.ViewModel
import com.assignment.arch.SingleLiveEvent
import com.assignment.presentation.viewmodel.SharedViewModel
import com.domain.entity.ErrorEntity
import com.domain.result.ResultState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.viewModel.base
 * Project: EITC du Assignment
 **/

open class BaseViewModel : ViewModel() {

    val loadingEvent = SingleLiveEvent<Boolean>()

    val errorEvent = SingleLiveEvent<ErrorEntity?>()

    private val compositeDisposable = CompositeDisposable()

    lateinit var sharedViewModel: SharedViewModel

    fun showLoading(show: Boolean) {
        loadingEvent.value = show
    }

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    fun setError(error: ErrorEntity?) {
        errorEvent.value = error
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun <T : Any> ResultState<T>.process(
        onSuccess: (value: T) -> Unit,
        onError: ((error: ErrorEntity?) -> Unit)? = null
    ) {
        when (this) {
            is ResultState.Success -> {
                onSuccess(data)
            }

            is ResultState.Error -> {
                onError?.let { onError(error) }
                    ?: setError(error)
            }

            else -> Unit
        }
    }
}