package com.example.rickandmortycompose.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortycompose.room.entity.FavoriteCharacter

@Dao
interface FavoriteCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(character: FavoriteCharacter)

    @Delete
    suspend fun removeFavorite(character: FavoriteCharacter)

    @Query("SELECT * FROM favorites")
    fun getFavorites(): LiveData<List<FavoriteCharacter>>

}
