package com.test.mvvm.data.dataprovider

import com.test.mvvm.data.Result
import retrofit2.Response

interface DataProviderImpl {

    suspend fun <T> getResponse(response: suspend() -> Response<T>): Result<T>
}
