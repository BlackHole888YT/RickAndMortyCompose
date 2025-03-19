package com.example.rickandmortycompose.data.dto.episodes

import com.google.gson.annotations.SerializedName

data class EpisResults(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "???",

    @SerializedName("air_date")
    val air_date: String = "???",

    @SerializedName("episode")
    val episode: String = "???",

    @SerializedName("characters")
    val characters: List<String>,

    @SerializedName("url")
    val url: String = "???",

    @SerializedName("created")
    val created: String = "???"
)
