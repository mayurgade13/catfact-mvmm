package com.test.mvvm.data

data class Result<out T>(
    val status: STATUS,
    val data: T?,
    val responseCode: Int?
) {

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(STATUS.SUCCESS, data, null)
        }

        fun <T> error(data: T?, responseCode: Int?): Result<T> {
            return Result(STATUS.ERROR, null, responseCode)
        }

        fun <T> loading(data: T?): Result<T> {
            return Result(STATUS.LOADING, data, null)
        }
    }

    enum class STATUS {
        SUCCESS,
        ERROR,
        LOADING
    }
}
