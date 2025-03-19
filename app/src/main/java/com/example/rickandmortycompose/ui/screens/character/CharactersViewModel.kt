package com.example.rickandmortycompose.ui.screens.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.data.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel()
{

    private val _charactersLiveData = MutableLiveData<List<CharResults>>()
    val charactersLiveData: LiveData<List<CharResults>> get() = _charactersLiveData

    fun fetchAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _charactersLiveData.postValue(charactersRepository.fetchAllCharacters())
        }
    }
}