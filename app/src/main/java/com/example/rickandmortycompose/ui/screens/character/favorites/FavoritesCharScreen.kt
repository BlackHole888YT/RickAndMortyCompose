package com.example.rickandmortycompose.ui.screens.character.favorites

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.room.model.RoomCharacter

@Composable
fun FavoritesCharScreen(
    favorites: List<RoomCharacter>,
    onRemoveFavorite: (RoomCharacter) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(Color.Gray)
    ) {
        Text(
            text = "Избранные персонажи",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, top = 8.dp)
        )

        if (favorites.isEmpty()) {
            Text(
                text = "Нет избранных персонажей",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        } else {
            LazyColumn {
                items(favorites) { character ->
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        FavoriteCharacterItem(
                            character = character,
                            onRemoveFavorite = onRemoveFavorite
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteCharacterItem(
    character: RoomCharacter,
    onRemoveFavorite: (RoomCharacter) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Favorite Icon",
            Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = character.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { onRemoveFavorite(character) }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_rate_24),
                contentDescription = "Remove from favorites",
                Modifier.size(60.dp),
                tint = Color.Yellow
            )
        }
    }
}
