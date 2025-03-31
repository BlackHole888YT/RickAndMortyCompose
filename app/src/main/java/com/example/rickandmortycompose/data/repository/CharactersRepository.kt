package com.example.rickandmortycompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycompose.data.api.CharacterApiService
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.data.pagingSource.character.CharacterPagingSource

class CharactersRepository constructor(
    private val characterApiService: CharacterApiService
    ){
    fun fetchAllCharacters(): Pager<Int, CharResults>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 15,
                initialLoadSize = 100,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(
                    characterApiService
                )
            }
        )
    }
}