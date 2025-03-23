package com.example.rickandmortycompose.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortycompose.room.dao.FavoriteCharacterDao
import com.example.rickandmortycompose.room.entity.FavoriteCharacter

@Database(entities = [FavoriteCharacter::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCharacterDao(): FavoriteCharacterDao
}
