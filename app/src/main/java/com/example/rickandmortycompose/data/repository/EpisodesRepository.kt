package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.api.EpisodesApiService
import com.example.rickandmortycompose.data.dto.episodes.EpisResults

class EpisodesRepository constructor(
    private val episodesApiService: EpisodesApiService
    ){
    suspend fun fetchAllCharacters(): List<EpisResults>{
        val episResponse = episodesApiService.fetchAllEpisodes()

        return if (episResponse.isSuccessful){
            episResponse.body()!!.results
        } else{
            emptyList()
        }
    }
}