package com.example.rickandmortycompose.ui.model

private const val STATUS_UNKNOWN = "Status.Unknown"
private const val STATUS_ALIVE = "Status.Alive"
private const val STATUS_DEAD = "Status.Dead"

object CharactersModel {
    val CharacterModel = listOf(
        CharactersData("url", "person_name1", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name2", STATUS_DEAD, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name3", STATUS_ALIVE, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name4", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name5", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name6", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name7", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name8", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name9", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name10", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name11", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
        CharactersData("url", "person_name12", STATUS_UNKNOWN, "Alien", "Male", "Earth"),
    )
}