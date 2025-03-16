package com.example.rickandmortycompose.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmortycompose.ui.model.CharacterItem
import com.example.rickandmortycompose.ui.model.CharactersData
import com.example.rickandmortycompose.ui.model.CharactersModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharactersScreen(onItemClick: (CharactersData) -> Unit){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("CharactersScreen")
                },
                modifier = Modifier.background(Color.Red)
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
                    items = CharactersModel.CharacterModel
                ) { it ->
                    CharacterItem(it) {
                        onItemClick(it)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ShowCharacterScreen(){
    CharactersScreen {

    }
}