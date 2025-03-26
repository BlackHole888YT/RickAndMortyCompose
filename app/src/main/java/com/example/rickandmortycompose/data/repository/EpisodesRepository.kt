package com.example.rickandmortycompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycompose.data.api.EpisodesApiService
import com.example.rickandmortycompose.data.dto.episodes.EpisResults
import com.example.rickandmortycompose.data.paging3.pagingSource.EpisodePagingSource

class EpisodesRepository constructor(
    private val episodesApiService: EpisodesApiService
    ){
    fun fetchAllCharacters(): Pager<Int, EpisResults> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 15,
                initialLoadSize = 100,
            ),
            pagingSourceFactory = {
                EpisodePagingSource(
                    episodesApiService
                )
            }
        )
    }
}