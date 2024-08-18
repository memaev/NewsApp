package com.example.newsapp.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.domain.entity.User

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User)

    @Delete
    fun removeUser(user: User)

    @Query("SELECT * FROM user WHERE email=:email")
    fun getUserByEmail(email: String): User?
}