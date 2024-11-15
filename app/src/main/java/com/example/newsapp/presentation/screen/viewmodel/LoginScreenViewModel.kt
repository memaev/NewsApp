package com.example.newsapp.presentation.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.AuthRepository
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.screen.state.LoginScreenEvent
import com.example.newsapp.presentation.screen.state.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginScreenState())
        private set

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.EmailUpdated -> this.state = state.copy(email = event.newEmail)
            is LoginScreenEvent.PasswordUpdated -> this.state = state.copy(password = event.newPassword)
            LoginScreenEvent.LoginBtnClicked -> login()
        }
    }
    private fun login() = viewModelScope.launch(Dispatchers.IO) {
        val email = state.email
        val password = state.password
        if (email.isEmpty() || password.isEmpty()) return@launch

        val result = authRepository.login(email, password)
        this@LoginScreenViewModel.state = state.copy(loginResult = result)
    }
}