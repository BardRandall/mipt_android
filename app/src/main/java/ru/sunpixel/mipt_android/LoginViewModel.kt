package ru.sunpixel.mipt_android

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    object ForgotPasswordClicked : LoginEvent()
    object LoginClicked : LoginEvent()
}

data class LoginViewState(
    val email: String = "",
    val password: String = ""
)

class LoginViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState> = _viewState;

    fun obtainEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EmailChanged -> emailChanged(event.value)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
            LoginEvent.ForgotPasswordClicked -> performForgotPassword()
            LoginEvent.LoginClicked -> performLogin()
        }
    }

    private fun emailChanged(value: String) {
        _viewState.value = _viewState.value.copy(email = value)
    }

    private fun passwordChanged(value: String) {
        _viewState.value = _viewState.value.copy(password = value)
    }

    private fun performForgotPassword() {

    }

    private fun performLogin() {

    }

}