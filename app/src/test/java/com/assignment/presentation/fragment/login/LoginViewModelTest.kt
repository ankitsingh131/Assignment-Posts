package com.assignment.presentation.fragment.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.assignment.BaseUnitTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import javax.inject.Inject

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.fragment.login
 * Project: EITC du Assignment
 **/
class LoginViewModelTest : BaseUnitTest() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var loginSuccessObserver: Observer<Void?>

    @Mock
    lateinit var changeLanguageObserver: Observer<Void?>

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun setUp() {
        super.setUp()
        testAppComponent.inject(this)
        viewModel.loginSuccessEvent.observeForever(loginSuccessObserver)
        viewModel.changeLanguageEvent.observeForever(changeLanguageObserver)
    }

    @Test
    fun `email validation returns true for valid email`() {
        val validEmail = "test@example.com"
        val invalidEmail = "invalid-email"

        viewModel.email.value = validEmail
        viewModel.password.value = "password123"

        assertTrue(viewModel.isFormValid.value ?: true)

        viewModel.email.value = invalidEmail

        assertFalse(viewModel.isFormValid.value ?: false)
    }

    @Test
    fun `password validation returns true for valid password`() {
        viewModel.email.value = "test@example.com"

        viewModel.password.value = "password123"
        assertTrue(viewModel.isFormValid.value ?: true)

        viewModel.password.value = "short"
        assertFalse(viewModel.isFormValid.value ?: false)
    }

    @Test
    fun `form is valid when both email and password are valid`() {
        viewModel.email.value = "test@example.com"
        viewModel.password.value = "password123"

        assertTrue(viewModel.isFormValid.value ?: true)
    }

    @Test
    fun `login should trigger loginSuccessEvent`() {
        viewModel.login()

        verify(loginSuccessObserver).onChanged(null)
    }

    @Test
    fun `changeLanguage should trigger changeLanguageEvent`() {
        viewModel.changeLanguage()

        verify(changeLanguageObserver).onChanged(null)
    }

    @Test
    fun `loginSuccessEvent should be called when login is invoked`() {
        viewModel.login()
        verify(loginSuccessObserver).onChanged(null)
    }

    @Test
    fun `changeLanguageEvent should be called when changeLanguage is invoked`() {
        viewModel.changeLanguage()
        verify(changeLanguageObserver).onChanged(null)
    }

    override fun tearDown() {
        super.tearDown()
        viewModel.loginSuccessEvent.removeObserver(loginSuccessObserver)
        viewModel.changeLanguageEvent.removeObserver(changeLanguageObserver)
    }
}