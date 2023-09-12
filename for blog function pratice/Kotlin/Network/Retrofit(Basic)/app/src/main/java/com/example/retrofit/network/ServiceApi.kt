package com.example.retrofit.network

import com.example.retrofit.model.PostResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("posts/{post}")
    suspend fun getPosts(@Path("post") post: String): PostResult

}