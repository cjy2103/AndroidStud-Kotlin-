package com.example.retrofit.repository

interface RetrofitCallback {
    fun onSuccess(result : String)
    fun onError(throwable: Throwable)
}