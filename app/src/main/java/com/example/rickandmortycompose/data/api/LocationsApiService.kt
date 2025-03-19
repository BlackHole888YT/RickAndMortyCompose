package com.example.rickandmortycompose.data.api

import com.example.rickandmortycompose.data.dto.locations.LocationsModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface LocationsApiService {

    @GET("location")
    suspend fun fetchAllLocations(): Response<LocationsModelDTO>
}