package com.example.newsapp.util

sealed interface Result {
    data class Success<T>(val msg: String = "", val data: T? = null): Result
    data class Failure<T>(val msg: String = "", val data: T? = null): Result
}