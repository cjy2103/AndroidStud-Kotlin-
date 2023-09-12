package com.example.retrofit.repository

import com.example.retrofit.network.RetrofitClient
import com.example.retrofit.network.ServiceApi

class MainRepository {

    private val service = RetrofitClient.getClient().create(ServiceApi::class.java)

    suspend fun callData(callback : RetrofitCallback) {
        try {
            val result = service.getPosts("1")
            callback.onSuccess(result.toString())
        } catch (e: Exception){
            callback.onError(e)
        }
    }
}