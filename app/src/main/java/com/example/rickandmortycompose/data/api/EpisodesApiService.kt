package com.example.rickandmortycompose.data.api

import com.example.rickandmortycompose.data.dto.episodes.EpisodesModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface EpisodesApiService {

    @GET("episode")
    suspend fun fetchAllEpisodes(): Response<EpisodesModelDTO>
}