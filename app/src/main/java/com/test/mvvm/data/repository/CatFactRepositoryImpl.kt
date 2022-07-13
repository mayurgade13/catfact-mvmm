package com.test.mvvm.data.repository

import com.test.mvvm.data.Result
import com.test.mvvm.data.api.ApiService
import com.test.mvvm.data.dataprovider.DataProvider
import com.test.mvvm.data.model.CatFactModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatFactRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): CatFactRepository, DataProvider() {

    override suspend fun getCatFact(): Flow<Result<CatFactModel>> {
        return flow {
            emit(Result.loading(null))
            val result = getResponse { apiService.getCatFact() }
            when(result.status) {
                Result.STATUS.SUCCESS -> emit(Result.success(result.data))
                Result.STATUS.ERROR -> emit(Result.error(null, result.responseCode))
                else -> { }
            }
        }.flowOn(Dispatchers.IO)
    }
}
