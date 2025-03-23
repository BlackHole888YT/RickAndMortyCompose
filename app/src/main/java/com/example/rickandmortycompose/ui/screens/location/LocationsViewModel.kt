package com.example.rickandmortycompose.ui.screens.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.locations.LocaResults
import com.example.rickandmortycompose.data.repository.LocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val locationsRepository: LocationsRepository
) : ViewModel()
{

    private val _locationsLiveData = MutableStateFlow<List<LocaResults>>(emptyList())
    val locationsStateFlow: StateFlow<List<LocaResults>> get() = _locationsLiveData

    fun fetchAllLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            locationsRepository.fetchAllCharacters().let {
                _locationsLiveData.value = it
            }
//            _locationsLiveData.postValue(locationsRepository.fetchAllCharacters())
        }
    }
}