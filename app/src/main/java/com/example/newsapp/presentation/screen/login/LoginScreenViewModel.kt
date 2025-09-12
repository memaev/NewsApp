package com.example.newsapp.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailUpdated -> onEmailUpdated(event.newEmail)
            is LoginScreenEvent.PasswordUpdated -> onPasswordUpdated(event.newPassword)
            LoginScreenEvent.LoginBtnClicked -> login()
        }
    }

    private fun onEmailUpdated(newEmail: String) {
        _state.update { it.copy(email = newEmail) }
    }

    private fun onPasswordUpdated(newPassword: String) {
        _state.update { it.copy(password = newPassword) }
    }

    private fun login() = viewModelScope.launch(Dispatchers.IO) {
        val email = state.value.email
        val password = state.value.password
        if (email.isEmpty() || password.isEmpty()) return@launch

        val result = authRepository.login(email, password)
        this@LoginScreenViewModel._state.update { it.copy(loginResult = result) }
    }
}