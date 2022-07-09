package com.test.mvvm.data.repository

import com.test.mvvm.data.Result
import com.test.mvvm.data.model.CatFactModel
import kotlinx.coroutines.flow.Flow

interface CatFactRepositoryImpl {

    suspend fun getCatFact(): Flow<Result<CatFactModel>>
}
