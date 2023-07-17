package com.example.youtubesearch.network

import com.example.youtubesearch.models.VideoYtModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/search")
    fun getVideo(
        @Query("part") part: String,
        @Query("q") q : String,
        @Query("maxResults") maxResult : Int
    ) : Call<VideoYtModel>

}