package com.example.rickandmortycompose.ui.activity.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.room.FavoriteCharacterViewModel
import com.example.rickandmortycompose.ui.model.character.CharactersData
import com.example.rickandmortycompose.ui.model.episode.EpisodesData
import com.example.rickandmortycompose.ui.model.location.LocationsData
import com.example.rickandmortycompose.ui.screens.character.CharactersScreen
import com.example.rickandmortycompose.ui.screens.character.detail.CharacterDetailScreen
import com.example.rickandmortycompose.ui.screens.character.favorites.FavoritesCharScreen
import com.example.rickandmortycompose.ui.screens.episode.EpisodesScreen
import com.example.rickandmortycompose.ui.screens.episode.detail.EpisodeDetailScreen
import com.example.rickandmortycompose.ui.screens.location.LocationsScreen
import com.example.rickandmortycompose.ui.screens.location.detail.LocationDetailScreen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun NavHostNavigation() {
    val navController = rememberNavController()
    val favoriteViewModel: FavoriteCharacterViewModel = koinViewModel()
    val favorites by favoriteViewModel.favorites.collectAsState(initial = emptyList())

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.e("bh", "Current route: $currentRoute")

    val showBottomBar = when (currentRoute) {
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.CharactersScreen",
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.EpisodesScreen",
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.LocationsScreen",
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.FavoritesCharScreen" -> true
        else -> false
    }

    val topAppBarText = when (currentRoute) {
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.CharactersScreen" -> "Character Screen"
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.EpisodesScreen" -> "Episodes Screen"
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.LocationsScreen" -> "Locations Screen"
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.FavoritesCharScreen" -> "Favorites Character Screen"
        else -> "Ты куда зашел че за экран??"
    }

    val showTopAppBar = when (currentRoute) {
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.CharactersScreen",
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.EpisodesScreen",
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.LocationsScreen",
        "com.example.rickandmortycompose.ui.activity.navigation.Navigation.FavoritesCharScreen" -> true
        else -> false
    }

    Log.e("bh","Show bottom bar: $showBottomBar")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (showTopAppBar){
                CenterAlignedTopAppBar(
                    modifier = Modifier,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Gray.copy(alpha = 0.3f)
                    ),
                    title = {
                        Text(
                            text = topAppBarText,
                        )
                    }
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier.height(100.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf(
                            ButtonModel(
                                navController,
                                R.drawable.person_outline,
                                "Character",
                                Navigation.CharactersScreen,
                                "Персонажи",
                                R.drawable.baseline_person_4_24
                            ),
                            ButtonModel(
                                navController,
                                R.drawable.baseline_live_tv_24,
                                "Episodes",
                                Navigation.EpisodesScreen,
                                "Эпизоды",
                                R.drawable.baseline_tv_24
                            ),
                            ButtonModel(
                                navController,
                                R.drawable.baseline_map_24,
                                "Locations",
                                Navigation.LocationsScreen,
                                "Локации",
                                R.drawable.map
                            ),
                            ButtonModel(
                                navController,
                                R.drawable.baseline_bookmark_border_24,
                                "Favorite Character Screen",
                                Navigation.FavoritesCharScreen,
                                "Избранное",
                                R.drawable.baseline_bookmark_24
                            )
                        )
                    }
                }
            }
        }
    ) {
        Box(
        ) {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = "CharactersScreen",
                        modifier = Modifier.offset(y = (10).dp)
                    )
                }
            )
            NavHost(navController, Navigation.CharactersScreen) {
                composable<Navigation.CharactersScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                ) {
                    CharactersScreen(
                        onItemClick = { character ->
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
                        }
                    )
                }
                composable<Navigation.EpisodesScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                ) {
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
                composable<Navigation.LocationsScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                ) {
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

                composable<Navigation.FavoritesCharScreen>(
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { it },
                            animationSpec = tween(300)
                        ) + fadeIn(animationSpec = tween(300))
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { -it },
                            animationSpec = tween(300)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                ) {
                    FavoritesCharScreen(
                        favorites = favorites.map { favorite -> favorite.toRoomCharacter() },
                        onRemoveFavorite = { character ->
                            favoriteViewModel.removeFavorite(character)
                        }
                    )
                }

                composable<Navigation.CharacterDetailScreen>(
                ) {
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
                composable<Navigation.EpisodeDetailScreen>(
                ) {
                    val args = it.toRoute<Navigation.EpisodeDetailScreen>()
                    EpisodeDetailScreen(
                        EpisodesData(
                            name = args.episName,
                            airDate = args.episAirDate,
                            episode = args.episEpisode,
                        )
                    )
                }

                composable<Navigation.LocationDetailScreen>(
                ) {
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
}

@Composable
fun ButtonModel(
    navController: NavController,
    defaultTexture: Int,
    contentDesk: String,
    navigateTo: Any,
    buttonDesk: String,
    clickedTexture: Int)
{
    val interactionSource = remember { MutableInteractionSource() }
    val interactionState = remember { mutableIntStateOf(defaultTexture) }

    val interaction by rememberUpdatedState(interactionSource.collectIsPressedAsState())

    LaunchedEffect(interaction.value) {
        if (interaction.value) {
            // Если кнопка нажата
            interactionState.intValue = clickedTexture
        } else {
            // Если кнопка не нажата
            coroutineScope {
                delay(2000)
                interactionState.intValue = defaultTexture
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {
            when (navigateTo) {
                is Navigation.CharactersScreen -> navController.navigate(Navigation.CharactersScreen)
                is Navigation.EpisodesScreen -> navController.navigate(Navigation.EpisodesScreen)
                is Navigation.LocationsScreen -> navController.navigate(Navigation.LocationsScreen)
                is Navigation.FavoritesCharScreen -> navController.navigate(Navigation.FavoritesCharScreen)
                else -> {}
            }
        },
            interactionSource = interactionSource
        ) {
            Icon(
                painter = painterResource(id = interactionState.intValue,),
                contentDescription = contentDesk
            )
        }
        Text(text = buttonDesk, modifier = Modifier.height(20.dp))
    }
}