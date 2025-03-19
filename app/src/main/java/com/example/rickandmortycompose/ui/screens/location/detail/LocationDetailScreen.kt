package com.example.rickandmortycompose.ui.screens.location.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.model.location.LocationsData

@Composable
fun LocationDetailScreen(location: LocationsData) {
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

        Icon(
            painter = painterResource(id = R.drawable.planet),
            contentDescription = "Location Icon",
            modifier = Modifier
                .size(150.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        location.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Тип: ${location.type}",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Cyan
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Измерение: ${location.dimension}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun PreviewLocationDetailScreen() {
    LocationDetailScreen(
        location = LocationsData(
            name = "Citadel of Ricks",
            type = "Space station",
            dimension = "Dimension C-137"
        )
    )
}