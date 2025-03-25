package com.example.rickandmortycompose.ui.screens.character

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.room.FavoriteCharacterViewModel
import com.example.rickandmortycompose.ui.model.character.CharacterItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharactersScreen(
    onItemClick: (CharResults) -> Unit,
    viewModel: CharactersViewModel = koinViewModel()
    ){

    LaunchedEffect(Unit) {
        viewModel.fetchAllCharacters()
    }

    val characters by viewModel.charactersStateFlow.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "CharactersScreen",
                        modifier = Modifier.offset(y = (10).dp)
                    )
                },
                modifier = Modifier
                    .background(Color.Red)
                    .height(90.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Gray)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                items(
                    items = characters
                ) { it ->
                    CharacterItem(
                        character = it,
                        onItemClick = onItemClick
                    )
                }

            }
        }
    }
}