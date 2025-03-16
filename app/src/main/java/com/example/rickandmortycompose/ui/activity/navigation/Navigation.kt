package com.example.rickandmortycompose.ui.activity.navigation

import kotlinx.serialization.Serializable

object Navigation {
    @Serializable
    object CharactersScreen

    @Serializable
    data class CharacterDetailScreen(
        var charName: String,
        var charStatus: String,
        var charKind: String,
        var charSex: String,
        var charLocation: String,
        var charAvatar: String,
    )

    @Serializable
    object EpisodesScreen

    @Serializable
    object EpisodeDetailScreen

    @Serializable
    object LocationsScreen

    @Serializable
    object LocationDetailScreen
}