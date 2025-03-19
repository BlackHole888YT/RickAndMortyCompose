package com.example.rickandmortycompose.data.dto.characters;

import com.google.gson.annotations.SerializedName

data class CharacterModelDTO(
    @SerializedName("info")
    val info: CharInfo,

    @SerializedName("results")
    val results: List<CharResults>
)
