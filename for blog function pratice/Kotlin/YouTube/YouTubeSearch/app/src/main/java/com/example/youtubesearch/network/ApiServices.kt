package com.example.youtubesearch.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/search")
    fun getVideo(
        @Query("part") part: String,
        @Query("q") q : String,
        @Query("maxResults") maxResult : Int
    )

}