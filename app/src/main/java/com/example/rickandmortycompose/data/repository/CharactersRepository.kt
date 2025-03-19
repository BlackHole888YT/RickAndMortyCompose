package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.api.CharacterApiService
import com.example.rickandmortycompose.data.dto.characters.CharResults

class CharactersRepository constructor(
    private val characterApiService: CharacterApiService
    ){
    suspend fun fetchAllCharacters(): List<CharResults>{
        val charResponse = characterApiService.fetchAllCharacters()

        return if (charResponse.isSuccessful){
            charResponse.body()!!.results
        } else{
            emptyList()
        }
    }
}