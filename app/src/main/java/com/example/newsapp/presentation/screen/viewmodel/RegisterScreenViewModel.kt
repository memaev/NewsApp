package com.example.newsapp.presentation.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.newsapp.presentation.screen.state.RegisterScreenEvent
import com.example.newsapp.presentation.screen.state.RegisterScreenState

class RegisterScreenViewModel : ViewModel() {
    var state by mutableStateOf(RegisterScreenState())
        private set

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.UsernameUpdated -> state = state.copy(username = event.newUsername)
            is RegisterScreenEvent.EmailUpdated -> state = state.copy(email = event.newEmail)
            is RegisterScreenEvent.PasswordUpdated -> state = state.copy(password = event.newPassword)
        }
    }
}