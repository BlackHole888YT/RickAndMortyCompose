package com.example.rickandmortycompose.ui.serviceLocator

import com.example.rickandmortycompose.ui.screens.character.CharactersViewModel
import com.example.rickandmortycompose.ui.screens.episode.EpisodesViewModel
import com.example.rickandmortycompose.ui.screens.location.LocationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { LocationsViewModel(get()) }
}