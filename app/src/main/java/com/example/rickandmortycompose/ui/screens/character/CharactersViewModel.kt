package com.example.rickandmortycompose.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.data.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel()
{
    private val _charactersStateFlow = MutableStateFlow<PagingData<CharResults>>(PagingData.empty())
    val charactersStateFlow: StateFlow<PagingData<CharResults>> get() = _charactersStateFlow

    fun fetchAllCharacters() {
        viewModelScope.launch {
            charactersRepository.fetchAllCharacters().flow.cachedIn(viewModelScope).collectLatest {
                _charactersStateFlow.value = it
            }
        }
    }
}