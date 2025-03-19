package com.example.rickandmortycompose.data.dto.episodes

import com.google.gson.annotations.SerializedName

data class EpisodesModelDTO (
    @SerializedName("info")
    val info: EpisInfo,

    @SerializedName("results")
    val results: List<EpisResults>
)