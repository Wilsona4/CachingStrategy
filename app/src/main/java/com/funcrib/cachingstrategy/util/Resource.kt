package com.funcrib.cachingstrategy.util

import retrofit2.Response

//// A generic class that contains data and status about loading this data.
//sealed class Resource<out T>(
//    val data: T? = null
//) {
//    class Success<T>(data: T) : Resource<T>(data)
//    class Loading<T>(data: T? = null) : Resource<T>(data)
//
//    //    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
//    data class Error(
//        val isNetworkError: Boolean,
//        val errorCode: Int?,
//        val errorBody: Response<Any>?
//    ) : Resource<Nothing>()
//}

sealed class Resource<T>(
    val data: T? = null,
    val errorBody: Response<Any>? = null,
    val isNetworkError: Boolean? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(isNetworkError: Boolean, errorBody: Response<Any>?, data: T? = null) : Resource<T>(data, errorBody, isNetworkError)
}