package com.test.mvvm.data.dataprovider

import com.test.mvvm.data.Result
import retrofit2.Response
import java.lang.Exception

abstract class DataProvider {

    suspend fun <T> getResponse(response: suspend() -> Response<T>): Result<T> {
        return try {
            val result = response.invoke()
            if (result.isSuccessful) {
                Result.success(result.body())
            } else {
                Result.error(result.body(), result.code())
            }
        } catch (e: Exception) {
            Result.error(null, DEFAULT_ERROR_CODE)
        }
    }

    companion object {
        private const val DEFAULT_ERROR_CODE = 1000
    }
}
