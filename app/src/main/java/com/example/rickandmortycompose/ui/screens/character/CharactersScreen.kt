package com.example.rickandmortycompose.ui.screens.character

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortycompose.app.loadState.ErrorView
import com.example.rickandmortycompose.app.loadState.LoadingView
import com.example.rickandmortycompose.app.loadState.NotLoadView
import com.example.rickandmortycompose.data.dto.characters.CharResults
import com.example.rickandmortycompose.ui.model.character.CharacterItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharactersScreen(
    onItemClick: (CharResults) -> Unit,
    viewModel: CharactersViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchAllCharacters()
    }

    val characters = viewModel.charactersStateFlow.collectAsLazyPagingItems()
    val isRefreshing = characters.loadState.refresh is LoadState.Loading
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { characters.refresh() }
    )

    Column(
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://i.pinimg.com/474x/3f/a8/ae/3fa8aeba6634acff6cdb5a3da2722109.jpg"),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 110.dp)
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

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}