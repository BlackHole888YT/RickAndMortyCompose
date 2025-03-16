package com.example.rickandmortycompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.rickandmortycompose.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.rickandmortycompose.ui.model.CharactersData


@Composable
fun CharacterDetailScreen(character: CharactersData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // Картинка персонажа
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Character Avatar",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Имя персонажа
        Text(
            text = character.name!!,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // Статус (Жив, Мёртв, Неизвестно)
        Text(
            text = "Статус: ${character.status}",
            style = MaterialTheme.typography.bodyLarge,
//            color = when (character.status) {
//                Status.Alive -> Color.Green
//                Status.Dead -> Color.Red
//                else -> {
//                    Color.Gray
//                }
//            }
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

// Превью экрана
@Preview
@Composable
fun PreviewCharacterDetailScreen() {
    CharacterDetailScreen(
        character = CharactersData(
            avatar = "",
            name = "Rick Sanchez",
            status = "dead",
//            status = Status.Alive,
            kind = "Human",
            sex = "Male",
            location = "Earth"
        )
    )
}
