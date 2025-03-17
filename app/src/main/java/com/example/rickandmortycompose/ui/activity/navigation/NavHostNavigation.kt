package com.example.rickandmortycompose.ui.activity.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.model.CharactersData
import com.example.rickandmortycompose.ui.screens.character.detail.CharacterDetailScreen
import com.example.rickandmortycompose.ui.screens.character.CharactersScreen
import com.example.rickandmortycompose.ui.screens.episode.EpisodesScreen
import com.example.rickandmortycompose.ui.screens.location.LocationsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun NavHostNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar {
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
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController, Navigation.CharactersScreen, modifier = Modifier.padding(paddingValues)) {
            composable<Navigation.CharactersScreen> {
                CharactersScreen(onItemClick = {
                    it.name?.let { name ->
                        it.status?.let { status ->
                            it.kind?.let { kind ->
                                it.sex?.let { sex ->
                                    it.location?.let { location ->
                                        it.avatar?.let { avatar ->
                                            navController.navigate(
                                                Navigation.CharacterDetailScreen(
                                                    name,
                                                    status,
                                                    kind,
                                                    sex,
                                                    location,
                                                    avatar
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //"characterDetail/${it.name}/${it.status}/${it.kind}/${it.sex}/${it.location}/${it.avatar}"
                })
            }
            composable<Navigation.EpisodesScreen> {
                EpisodesScreen()
            }
            composable<Navigation.LocationsScreen> {
                LocationsScreen()
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
                //EpisodesDetailScreen()
            }
            composable<Navigation.LocationDetailScreen> {
                //LocationsDetailScreen()
            }
        }
    }
}
