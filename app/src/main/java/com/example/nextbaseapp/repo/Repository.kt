package com.example.nextbaseapp.repo

import com.example.nextbaseapp.api.ApiService
import com.example.nextbaseapp.api.RetrofitClient
import com.example.nextbaseapp.model.Journey_model
import com.example.nextbaseapp.util.retryIO
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor() {

    suspend fun getData(): Response<Journey_model> {
        return retryIO{RetrofitClient.getService(ApiService::class.java)
            .getResponse()}
    }
}