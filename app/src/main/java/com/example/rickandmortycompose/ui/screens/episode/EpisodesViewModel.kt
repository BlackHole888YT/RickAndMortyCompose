package com.example.rickandmortycompose.ui.screens.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rickandmortycompose.data.dto.episodes.EpisResults
import com.example.rickandmortycompose.data.repository.EpisodesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val episodesRepository: EpisodesRepository
) : ViewModel()
{
    private val _episodesLiveData = MutableStateFlow<PagingData<EpisResults>>(PagingData.empty())
    val episodesStateFlow: StateFlow<PagingData<EpisResults>> get() = _episodesLiveData

    fun fetchAllEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodesRepository.fetchAllCharacters().flow.collectLatest() {
                _episodesLiveData.value = it
            }
        }
    }
}