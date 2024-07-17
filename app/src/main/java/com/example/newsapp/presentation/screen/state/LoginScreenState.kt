package com.example.newsapp.presentation.screen.state

sealed class LoginScreenEvent {
    data class EmailUpdated(val newEmail: String): LoginScreenEvent()
    data class PasswordUpdated(val newPassword: String): LoginScreenEvent()
}

data class LoginScreenState(
    val email: String = "",
    val password: String = ""
)
