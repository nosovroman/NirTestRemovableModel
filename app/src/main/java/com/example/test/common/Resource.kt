package com.example.test.common

sealed class Resource<T>(val data: T? = null, val message: String? = null, val time: Long? = null) {
    class Success<T>(data: T, time: Long) : Resource<T>(data = data, time = time)
    class Error<T>(message: String, data: T? = null) : Resource<T>(message = message, data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Empty<T>(time: Long) : Resource<T>(time = time)
}
