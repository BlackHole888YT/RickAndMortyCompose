package com.example.rickandmortycompose.data.dto.locations;

import com.google.gson.annotations.SerializedName

data class LocaResults(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "???",

    @SerializedName("type")
    val type: String = "???",

    @SerializedName("dimension")
    val dimension: String = "???",

    @SerializedName("residents")
    val residents: List<String>,

    @SerializedName("url")
    val url: String = "???",

    @SerializedName("created")
    val created: String = "???"
)