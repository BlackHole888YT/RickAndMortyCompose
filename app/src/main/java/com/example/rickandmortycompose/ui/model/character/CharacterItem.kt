package com.example.rickandmortycompose.ui.model.character

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.room.FavoriteCharacterViewModel
import com.example.rickandmortycompose.room.model.RoomCharacter
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterItem(
    character: CharResults,
    onItemClick: (CharResults) -> Unit,
    viewModel: FavoriteCharacterViewModel = koinViewModel()
) {
    val isFavorite by viewModel.isFavorite.collectAsState()
    val isCharacterFavorite = isFavorite[character.id] ?: false

    LaunchedEffect(character.id) {
        viewModel.checkFavoriteStatus(character.id)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onItemClick(character)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B2D42)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Статус: ${character.status}",
                        color = Color.Cyan,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            IconButton(
                onClick = {
                    val roomCharacter = RoomCharacter(
                        id = character.id,
                        name = character.name,
                        status = character.status
                    )
                    if (isCharacterFavorite) {
                        viewModel.removeFavorite(roomCharacter)
                    } else {
                        viewModel.addFavorite(roomCharacter)
                    }
                },
                modifier = Modifier.size(40.dp)
            ) {
                AnimatedContent(
                    targetState = isCharacterFavorite,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(300)) togetherWith
                        fadeOut(animationSpec = tween(300))
                    }
                ) { favorite ->
                    Image(
                        painter = painterResource(
                            id = if (favorite) {
                                R.drawable.baseline_star_rate_24
                            } else {
                                R.drawable.baseline_star_outline_24
                            }
                        ),
                        contentDescription = if (favorite) "Remove from favorites" else "Add to favorites",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
