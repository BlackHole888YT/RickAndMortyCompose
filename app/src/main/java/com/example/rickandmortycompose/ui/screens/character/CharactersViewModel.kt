package com.example.rickandmortycompose.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.data.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel()
{
    private val _charactersLiveData = MutableStateFlow<List<CharResults>>(emptyList())
    val charactersStateFlow: StateFlow<List<CharResults>> get() = _charactersLiveData

    fun fetchAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersRepository.fetchAllCharacters().let {
                _charactersLiveData.value = it
            }
//            _charactersLiveData.postValue(charactersRepository.fetchAllCharacters())
        }
    }
}