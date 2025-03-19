package com.example.rickandmortycompose.ui.screens.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.locations.LocaResults
import com.example.rickandmortycompose.data.repository.LocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val locationsRepository: LocationsRepository
) : ViewModel()
{

    private val _locationsLiveData = MutableLiveData<List<LocaResults>>()
    val locationsLiveData: LiveData<List<LocaResults>> get() = _locationsLiveData

    fun fetchAllLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            _locationsLiveData.postValue(locationsRepository.fetchAllCharacters())
        }
    }
}