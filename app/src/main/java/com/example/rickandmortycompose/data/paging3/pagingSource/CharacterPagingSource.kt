package com.example.rickandmortycompose.data.paging3.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycompose.data.api.CharacterApiService
import com.example.rickandmortycompose.data.dto.characters.CharResults
import retrofit2.HttpException
import okio.IOException

class CharacterPagingSource(
    private val characterApiService: CharacterApiService
) : PagingSource<Int, CharResults>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharResults> {
        return try {
            val pageNumber = params.key ?: 1
            val response = characterApiService.fetchAllCharacters(pageNumber)
            val prevPage = if (pageNumber > 0) pageNumber - 1 else null
            val nextPage = if (response.body()?.info?.next != null) pageNumber + 1 else null

            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (ex: HttpException){
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharResults>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorCharPage = state.closestPageToPosition(anchorPosition)
            anchorCharPage?.prevKey?.plus(1) ?: anchorCharPage?.nextKey?.minus(1)
        }
    }
}