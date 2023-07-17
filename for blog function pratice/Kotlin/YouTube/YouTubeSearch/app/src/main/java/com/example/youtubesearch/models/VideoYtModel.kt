package com.example.youtubesearch.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VideoYtModel(
    @SerializedName("nextPageToken")
    val nextPageToken: String,
    @SerializedName("items")
    val items: VideoItem
){
    data class VideoItem(
        @SerializedName("snippet")
        val snippetYt: SnippetYt
    )
}