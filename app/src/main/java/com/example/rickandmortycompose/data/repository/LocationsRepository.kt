package com.example.rickandmortycompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycompose.data.api.LocationsApiService
import com.example.rickandmortycompose.data.dto.locations.LocaResults
import com.example.rickandmortycompose.data.pagingSource.location.LocationPagingSource

class LocationsRepository constructor(
    private val locationsApiService: LocationsApiService
    ){
    fun fetchAllCharacters(): Pager<Int,LocaResults> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 15,
                initialLoadSize = 100,
            ),
            pagingSourceFactory = {
                LocationPagingSource(
                    locationsApiService
                )
            }
        )
    }
}