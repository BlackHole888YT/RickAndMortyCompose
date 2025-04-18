package com.example.rickandmortycompose.ui.screens.episode.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.model.episode.EpisodesData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailScreen(episode: EpisodesData) {
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
                Text(
                    text = episode.name,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        )
        Spacer(modifier = Modifier.height(20.dp))

        Icon(
            painter = painterResource(id = R.drawable.baseline_tv_24),
            contentDescription = "Episode Icon",
            tint = Color.White,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Дата выхода: ${episode.airDate}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Cyan
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Эпизод: ${episode.episode}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Green
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEpisodeDetailScreen() {
    EpisodeDetailScreen(
        episode = EpisodesData(
            name = "Pilot",
            airDate = "December 2, 2013",
            episode = "S01E01"
        )
    )
}
