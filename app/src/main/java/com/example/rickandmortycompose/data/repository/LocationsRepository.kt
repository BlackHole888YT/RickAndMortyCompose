package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.api.LocationsApiService
import com.example.rickandmortycompose.data.dto.locations.LocaResults

class LocationsRepository constructor(
    private val locationsApiService: LocationsApiService
    ){
    suspend fun fetchAllCharacters(): List<LocaResults>{
        val locaResponse = locationsApiService.fetchAllLocations()

        return if (locaResponse.isSuccessful){
            locaResponse.body()!!.results
        } else{
            emptyList()
        }
    }
}