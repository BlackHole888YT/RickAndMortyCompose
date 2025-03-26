package com.example.rickandmortycompose.data.paging3.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycompose.data.api.LocationsApiService
import com.example.rickandmortycompose.data.dto.locations.LocaResults
import retrofit2.HttpException
import okio.IOException

class LocationPagingSource(
    private val locationsApiService: LocationsApiService
) : PagingSource<Int, LocaResults>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocaResults> {
        return try {
            val pageLocaNumber = params.key ?: 1
            val locaResponse = locationsApiService.fetchAllLocations(pageLocaNumber)
            val prevLocaPage = if (pageLocaNumber > 0) pageLocaNumber - 1 else null
            val nextLocaPage = if (locaResponse.body()?.info?.next != null) pageLocaNumber + 1 else null

            LoadResult.Page(
                data = locaResponse.body()?.results ?: emptyList(),
                prevKey = prevLocaPage,
                nextKey = nextLocaPage
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (ex: HttpException){
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocaResults>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorCharPage = state.closestPageToPosition(anchorPosition)
            anchorCharPage?.prevKey?.plus(1) ?: anchorCharPage?.nextKey?.minus(1)
        }
    }
}