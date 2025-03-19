package com.example.rickandmortycompose.data.dto.characters;

import com.google.gson.annotations.SerializedName

data class CharLocation(
    @SerializedName("name")
    val name: String = "???",

    @SerializedName("url")
    val url: String = "???"
)