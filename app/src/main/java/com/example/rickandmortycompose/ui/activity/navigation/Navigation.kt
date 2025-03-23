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
    data class EpisodeDetailScreen(
        var episName: String,
        var episAirDate: String,
        var episEpisode: String,
    )

    @Serializable
    object LocationsScreen

    @Serializable
    data class LocationDetailScreen(
        var locaName: String,
        var locaType: String,
        var locaDimension: String,
    )

    @Serializable
    object FavoritesCharScreen
}