package com.example.rickandmortycompose.ui.model.character

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
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
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(100)
    )

    val imagePainter = rememberAsyncImagePainter(character.image)

    LaunchedEffect(character.id) {
        viewModel.checkFavoriteStatus(character.id)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onItemClick(character)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.3f)
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
                    painter = imagePainter,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.Gray)
                        .border(width = 2.dp, color = Color(0xFFFF0000), shape = CircleShape)
                    ,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = character.name,
                        style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 7.5f)),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Статус: ${character.status}",
                        color = Color.Cyan,
                        style = TextStyle(shadow = Shadow(color = Color.Black, blurRadius = 7.5f)),
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
