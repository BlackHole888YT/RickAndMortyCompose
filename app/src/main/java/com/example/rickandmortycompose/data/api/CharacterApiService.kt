package com.example.rickandmortycompose.data.api

import com.example.rickandmortycompose.data.dto.characters.CharacterModelDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {

    @GET("character")
    suspend fun fetchAllCharacters(
        @Query("page") page: Int
    ): Response<CharacterModelDTO>
}