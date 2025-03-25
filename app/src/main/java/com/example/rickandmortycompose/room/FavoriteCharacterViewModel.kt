package com.example.rickandmortycompose.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.room.database.AppDatabase
import com.example.rickandmortycompose.room.entity.FavoriteCharacter
import com.example.rickandmortycompose.room.model.RoomCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteCharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteCharacterDao = AppDatabase.getDatabase(application).favoriteCharacterDao()

    private val _favorites = MutableStateFlow<List<FavoriteCharacter>>(emptyList())
    val favorites: StateFlow<List<FavoriteCharacter>> = _favorites.asStateFlow()

    private val _isFavorite = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val isFavorite: StateFlow<Map<Int, Boolean>> = _isFavorite

    init {
        viewModelScope.launch {
            favoriteCharacterDao.getFavorites().collect { favorites ->
                _favorites.value = favorites
            }
        }
    }

    // Добавить персонажа в избранное
    fun addFavorite(character: RoomCharacter) {
        viewModelScope.launch {
            if (!favoriteCharacterDao.isFavorite(character.id)) {
                favoriteCharacterDao.addFavorite(FavoriteCharacter.fromRoomCharacter(character))
                updateFavoriteStatus(character.id, true)
            }
        }
    }

    // Удалить персонажа из избранного
    fun removeFavorite(character: RoomCharacter) {
        viewModelScope.launch {
            if (favoriteCharacterDao.isFavorite(character.id)) {
                favoriteCharacterDao.removeFavorite(FavoriteCharacter.fromRoomCharacter(character))
                updateFavoriteStatus(character.id, false)
            }
        }
    }

    // Проверить статус избранного для персонажа
    fun checkFavoriteStatus(characterId: Int) {
        viewModelScope.launch {
            val isFavorite = favoriteCharacterDao.isFavorite(characterId)
            updateFavoriteStatus(characterId, isFavorite)
        }
    }

    private fun updateFavoriteStatus(characterId: Int, isFavorite: Boolean) {
        _isFavorite.value = _isFavorite.value + (characterId to isFavorite)
    }
}
