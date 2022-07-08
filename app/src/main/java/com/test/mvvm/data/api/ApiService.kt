package com.test.mvvm.data.api

import com.test.mvvm.data.model.CatFactModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/fact")
    suspend fun getCatFact(): Response<CatFactModel>
}
