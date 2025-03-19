package com.example.rickandmortycompose.ui.screens.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.episodes.EpisResults
import com.example.rickandmortycompose.data.repository.EpisodesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val episodesRepository: EpisodesRepository
) : ViewModel()
{

    private val _episodesLiveData = MutableLiveData<List<EpisResults>>()
    val episodesLiveData: LiveData<List<EpisResults>> get() = _episodesLiveData

    fun fetchAllEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            _episodesLiveData.postValue(episodesRepository.fetchAllCharacters())
        }
    }
}