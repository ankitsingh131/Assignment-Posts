package com.assignment.presentation.fragment.login

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.arch.SingleLiveEvent
import com.assignment.presentation.constant.Constants
import com.assignment.presentation.viewmodel.base.BaseViewModel
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.login
 * Project: EITC du Assignment
 **/
class LoginViewModel @Inject constructor() : BaseViewModel() {

    var selectedLanguage = Constants.DEFAULT_LANGUAGE
    val email = MutableLiveData<String>("e@e.e")
    val password = MutableLiveData<String>("12345678")

    private val _isFormValid = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = isEmailValid(it) && isPasswordValid(password.value) }
        addSource(password) { value = isEmailValid(email.value) && isPasswordValid(it) }
    }
    val isFormValid: LiveData<Boolean> get() = _isFormValid

    val loginSuccessEvent = SingleLiveEvent<Void?>()
    val changeLanguageEvent = SingleLiveEvent<String>()

    private fun isEmailValid(email: String?): Boolean {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String?): Boolean {
        return password != null && password.length in 8..15
    }

    fun login() {
        loginSuccessEvent.call()
    }

    fun changeLanguage() {
        selectedLanguage = if (selectedLanguage == Constants.LANGUAGE_ENGLISH) {
            Constants.LANGUAGE_ARABIC
        } else {
            Constants.LANGUAGE_ENGLISH
        }

        changeLanguageEvent.value = selectedLanguage
    }
}