package com.example.rickandmortycompose.ui.screens.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortycompose.data.dto.locations.LocaResults
import com.example.rickandmortycompose.data.repository.LocationsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val locationsRepository: LocationsRepository
) : ViewModel()
{
    private val _locationsLiveData = MutableStateFlow<PagingData<LocaResults>>(PagingData.empty())
    val locationsStateFlow: StateFlow<PagingData<LocaResults>> get() = _locationsLiveData

    fun fetchAllLocations() {
        viewModelScope.launch {
            locationsRepository.fetchAllCharacters().flow.cachedIn(viewModelScope).collectLatest {
                _locationsLiveData.value = it
            }
        }
    }
}