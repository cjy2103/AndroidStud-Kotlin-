package com.example.youtubesearch.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class SnippetYt(

    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("customUrl")
    val customUrl: String,

    @SerializedName("publishedAt")
    val publishedAt: String,
    
    @SerializedName("country")
    val country: String

)