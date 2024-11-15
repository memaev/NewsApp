package com.example.newsapp.presentation.screen.state

import com.example.newsapp.util.Result

sealed class LoginScreenEvent {
    data class EmailUpdated(val newEmail: String): LoginScreenEvent()
    data class PasswordUpdated(val newPassword: String): LoginScreenEvent()
    data object LoginBtnClicked: LoginScreenEvent()
}

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val loginResult: Result? = null
)
