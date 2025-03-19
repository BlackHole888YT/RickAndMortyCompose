package com.example.rickandmortycompose.data.dto.episodes;

import com.google.gson.annotations.SerializedName

data class EpisInfo(
    @SerializedName("count")
    val count: Int = 0,

    @SerializedName("pages")
    val pages: Int = 0,

    @SerializedName("next")
    val next: String? = null,

    @SerializedName("prev")
    val prev: String? = null
)