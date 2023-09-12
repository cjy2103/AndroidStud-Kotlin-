package com.example.retrofit.repository

interface RetrofitCallback {
    fun onSuccess(result : String)
    fun onFailed()
    fun onError(throwable: Throwable)
}