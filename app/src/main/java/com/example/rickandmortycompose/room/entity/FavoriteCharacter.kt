package com.example.rickandmortycompose.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortycompose.room.model.RoomCharacter

@Entity(tableName = "favorites")
data class FavoriteCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String
) {
    fun toRoomCharacter() = RoomCharacter(
        id = id,
        name = name,
        status = status
    )
    
    companion object {
        fun fromRoomCharacter(character: RoomCharacter) = FavoriteCharacter(
            id = character.id,
            name = character.name,
            status = character.status
        )
    }
}
