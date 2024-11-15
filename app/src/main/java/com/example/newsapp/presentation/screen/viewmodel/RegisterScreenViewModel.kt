package com.example.newsapp.presentation.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repository.AuthRepository
import com.example.newsapp.presentation.screen.state.RegisterScreenEvent
import com.example.newsapp.presentation.screen.state.RegisterScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    var state by mutableStateOf(RegisterScreenState())
        private set

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.UsernameUpdated -> state = state.copy(username = event.newUsername)
            is RegisterScreenEvent.EmailUpdated -> state = state.copy(email = event.newEmail)
            is RegisterScreenEvent.PasswordUpdated -> state = state.copy(password = event.newPassword)
            is RegisterScreenEvent.RegisterBtnClicked -> register()
        }
    }

    private fun register() = viewModelScope.launch(Dispatchers.IO) {
        val username = state.username
        val email = state.email
        val password = state.password
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) return@launch

        val result = authRepository.register(username, email, password)
        this@RegisterScreenViewModel.state = state.copy(registerResult = result)
    }
}