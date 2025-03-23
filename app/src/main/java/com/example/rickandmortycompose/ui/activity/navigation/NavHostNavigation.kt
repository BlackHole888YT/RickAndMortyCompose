package com.example.rickandmortycompose.ui.activity.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.model.character.CharactersData
import com.example.rickandmortycompose.ui.model.episode.EpisodesData
import com.example.rickandmortycompose.ui.model.location.LocationsData
import com.example.rickandmortycompose.ui.screens.character.detail.CharacterDetailScreen
import com.example.rickandmortycompose.ui.screens.character.CharactersScreen
import com.example.rickandmortycompose.ui.screens.character.favorites.FavoritesCharScreen
import com.example.rickandmortycompose.ui.screens.episode.EpisodesScreen
import com.example.rickandmortycompose.ui.screens.episode.detail.EpisodeDetailScreen
import com.example.rickandmortycompose.ui.screens.location.LocationsScreen
import com.example.rickandmortycompose.ui.screens.location.detail.LocationDetailScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun NavHostNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(80.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        navController.navigate(Navigation.CharactersScreen)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.button),
                            contentDescription = "Button 1"
                        )
                    }

                    IconButton(onClick = {
                       navController.navigate(Navigation.EpisodesScreen)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.button),
                            contentDescription = "Button 2"
                        )
                    }

                    IconButton(onClick = {
                        navController.navigate(Navigation.LocationsScreen)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.button),
                            contentDescription = "Button 3"
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(Navigation.FavoritesCharScreen)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Button 4"
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController, Navigation.CharactersScreen, modifier = Modifier.padding(paddingValues)) {

            composable<Navigation.CharactersScreen> {
                CharactersScreen(onItemClick = { character ->
                    navController.navigate(
                        Navigation.CharacterDetailScreen(
                            charName = character.name,
                            charStatus = character.status,
                            charKind = character.species,
                            charSex = character.gender,
                            charLocation = character.location.name,
                            charAvatar = character.image,
                        )
                    )
                })
            }
            composable<Navigation.EpisodesScreen> {
                EpisodesScreen(onEpisClick = { episode ->
                    navController.navigate(
                        Navigation.EpisodeDetailScreen(
                            episName = episode.name,
                            episAirDate = episode.air_date,
                            episEpisode = episode.episode
                        )
                    )
                })
            }
            composable<Navigation.LocationsScreen> {
                LocationsScreen(onLocaClick = {location ->
                    navController.navigate(
                        Navigation.LocationDetailScreen(
                            locaName = location.name,
                            locaType = location.type,
                            locaDimension = location.dimension,
                        )
                    )
                })
            }

            composable<Navigation.FavoritesCharScreen> {
                FavoritesCharScreen(listOf("ee", "ee"))
            }

            composable<Navigation.CharacterDetailScreen> {
                val args = it.toRoute<Navigation.CharacterDetailScreen>()
                CharacterDetailScreen(
                    CharactersData(
                        avatar = args.charAvatar,
                        name = args.charName,
                        status = args.charStatus,
                        kind = args.charKind,
                        sex = args.charSex,
                        location = args.charLocation
                    )
                )
            }
            composable<Navigation.EpisodeDetailScreen> {
                val args = it.toRoute<Navigation.EpisodeDetailScreen>()
                EpisodeDetailScreen(
                    EpisodesData(
                        name = args.episName,
                        airDate = args.episAirDate,
                        episode = args.episEpisode,
                    )
                )
            }

            composable<Navigation.LocationDetailScreen> {
                val args = it.toRoute<Navigation.LocationDetailScreen>()
                LocationDetailScreen(
                    LocationsData(
                        name = args.locaType,
                        type = args.locaName,
                        dimension = args.locaDimension
                    )
                )
            }
        }
    }
}
