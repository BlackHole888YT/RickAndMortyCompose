package com.example.rickandmortycompose.ui.screens.character

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.data.paging3.loadState.ErrorView
import com.example.rickandmortycompose.data.paging3.loadState.LoadingView
import com.example.rickandmortycompose.data.paging3.loadState.NotLoadView
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

    val characters = viewModel.charactersStateFlow.collectAsLazyPagingItems()

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
                    count = characters.itemCount
                ) { index ->
                    characters[index]?.let {
                        CharacterItem(
                            character = it,
                            onItemClick = onItemClick
                        )
                    }
                }

                characters.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingView() }
                        }
                        loadState.append is LoadState.Loading -> {
                            item { LoadingView() }
                        }

                        loadState.append is LoadState.NotLoading -> {
                            item { NotLoadView() }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = (loadState.refresh as LoadState.Error).error
                            item { ErrorView(error.message ?: "Unknown Error") { retry() } }
                        }
                        loadState.append is LoadState.Error -> {
                            val error = (loadState.append as LoadState.Error).error
                            item { ErrorView(error.message ?: "Unknown Error") { retry() } }
                        }
                    }
                }
            }
        }
    }
}