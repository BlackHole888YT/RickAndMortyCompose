package com.example.rickandmortycompose.room

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.room.database.AppDatabase
import com.example.rickandmortycompose.room.entity.FavoriteCharacter
import kotlinx.coroutines.launch

class FavoriteCharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteCharacterDao = AppDatabase.getDatabase(application).favoriteCharacterDao()

    val favorites: LiveData<List<FavoriteCharacter>> = favoriteCharacterDao.getFavorites()

    // Добавить персонажа в избранное
    fun addFavorite(character: Character) {
        viewModelScope.launch {
            favoriteCharacterDao.addFavorite(FavoriteCharacter(character.id, character.name, character.status))
        }
    }

    // Удалить персонажа из избранного
    fun removeFavorite(character: Character) {
        viewModelScope.launch {
            favoriteCharacterDao.removeFavorite(FavoriteCharacter(character.id, character.name, character.status))
        }
    }
}
