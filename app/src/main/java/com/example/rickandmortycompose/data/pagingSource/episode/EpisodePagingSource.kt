package com.example.rickandmortycompose.data.pagingSource.episode

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycompose.data.api.EpisodesApiService
import com.example.rickandmortycompose.data.dto.episodes.EpisResults
import retrofit2.HttpException
import okio.IOException

class EpisodePagingSource(
    private val episodesApiService: EpisodesApiService
) : PagingSource<Int, EpisResults>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisResults> {
        return try {
            val pageEpisNumber = params.key ?: 1
            val episResponse = episodesApiService.fetchAllEpisodes(pageEpisNumber)
            val prevEpisPage = if (pageEpisNumber > 0) pageEpisNumber - 1 else null
            val nextEpisPage = if (episResponse.body()?.info?.next != null){
                episResponse.body()?.info?.next?.toUri()?.getQueryParameter("page")?.toInt()
            }else null

            LoadResult.Page(
                data = episResponse.body()?.results ?: emptyList(),
                prevKey = prevEpisPage,
                nextKey = nextEpisPage
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (ex: HttpException){
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisResults>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorCharPage = state.closestPageToPosition(anchorPosition)
            anchorCharPage?.prevKey?.plus(1) ?: anchorCharPage?.nextKey?.minus(1)
        }
    }
}