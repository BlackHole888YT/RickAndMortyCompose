package com.example.rickandmortycompose.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
)
