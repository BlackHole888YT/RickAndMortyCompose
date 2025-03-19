package com.example.rickandmortycompose.data.dto.locations

import com.google.gson.annotations.SerializedName

data class LocationsModelDTO(
    @SerializedName("info")
    val info: LocaInfo,

    @SerializedName("results")
    val results: List<LocaResults>
)
