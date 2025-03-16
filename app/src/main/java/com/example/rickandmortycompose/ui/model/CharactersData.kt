package com.example.rickandmortycompose.ui.model

data class CharactersData(
    var avatar: String? = null,
    var name: String? = null,
    var status: String? = null,
    var kind: String? = null, //вид
    var sex: String? = null, //пол
    var location: String? = null,
)

//enum class Status{
//    Dead,
//    Alive,
//    Unknown,
//}
