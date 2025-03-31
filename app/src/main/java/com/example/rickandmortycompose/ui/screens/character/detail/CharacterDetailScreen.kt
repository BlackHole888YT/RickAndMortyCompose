package com.example.rickandmortycompose.ui.screens.character.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortycompose.ui.model.character.CharactersData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(character: CharactersData) {
    val imagePainter = rememberAsyncImagePainter(character.avatar)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black.copy(alpha = 0.3f)
            ),
            title = {
                character.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Image(
            painter = imagePainter,
            contentDescription = "Character Avatar",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Статус: ${character.status}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Cyan
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Вид: ${character.kind}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(
            text = "Пол: ${character.sex}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Местоположение: ${character.location}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun PreviewCharacterDetailScreen() {
    CharacterDetailScreen(
        character = CharactersData(
            avatar = "",
            name = "Rick Sanchez",
            status = "dead",
            kind = "Human",
            sex = "Male",
            location = "Earth"
        )
    )
}
