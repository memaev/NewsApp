package com.example.newsapp.data.repository

import com.example.newsapp.domain.dao.UserDao
import com.example.newsapp.domain.entity.User
import com.example.newsapp.domain.util.Result
import java.util.UUID
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userDao: UserDao,
    private val localAuthManager: LocalAuthManager
) {
    suspend fun login(email: String, password: String): Result {
        val loginUser = userDao.login(email, password)
        val result = if (loginUser == null) Result.Failure<Unit>("Login failed. Check your credentials")
        else {
            localAuthManager.rememberAuth(loginUser.id)
            Result.Success<Unit>("Successfully logged in")
        }

        return result
    }

    suspend fun register(username: String, email: String, password: String): Result {
        if (userDao.getUserByEmail(email) != null)
            return Result.Failure<Unit>("User with this email already exists")

        val user = User(
            id = UUID.randomUUID().toString(),
            username = username,
            email = email,
            password = password
        )
        userDao.addUser(user)

        localAuthManager.rememberAuth(user.id)

        return Result.Success<Unit>("Successfully registered")
    }
}