package com.example.rickandmortycompose.ui.screens.episode

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
import com.example.rickandmortycompose.data.dto.episodes.EpisResults
import com.example.rickandmortycompose.data.paging3.loadState.ErrorView
import com.example.rickandmortycompose.data.paging3.loadState.LoadingView
import com.example.rickandmortycompose.data.paging3.loadState.NotLoadView
import com.example.rickandmortycompose.ui.model.episode.EpisodeItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesScreen(
    onEpisClick: (EpisResults) -> Unit,
    viewModel: EpisodesViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchAllEpisodes()
    }

    val episodes = viewModel.episodesStateFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Episodes", modifier = Modifier.offset(y = 10.dp)) },
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
                    count = episodes.itemCount
                ) { index ->
                    episodes[index]?.let {
                        EpisodeItem(it) {
                            onEpisClick(it)
                        }
                    }
                }
                episodes.apply {
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
