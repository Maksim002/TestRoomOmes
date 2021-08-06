package com.timelysoft.tsjdomcom.service


import com.beksar.testnotification.service.result.ExampleModel
import retrofit2.Response
import retrofit2.http.*
import java.lang.Exception


interface ApiService {
    @GET("v1/countries?")
    suspend fun news(): Response<ExampleModel>
}

