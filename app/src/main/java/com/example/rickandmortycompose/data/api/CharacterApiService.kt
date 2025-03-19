package com.example.rickandmortycompose.data.api

import com.example.rickandmortycompose.data.dto.characters.CharacterModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {

    @GET("character")
    suspend fun fetchAllCharacters(): Response<CharacterModelDTO>
}