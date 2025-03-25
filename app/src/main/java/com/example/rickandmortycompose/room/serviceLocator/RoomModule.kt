package com.example.rickandmortycompose.room.serviceLocator

import com.example.rickandmortycompose.room.FavoriteCharacterViewModel
import com.example.rickandmortycompose.room.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomModule = module {
    single { AppDatabase.getDatabase(androidApplication()) }
    single { get<AppDatabase>().favoriteCharacterDao() }
    viewModel { FavoriteCharacterViewModel(androidApplication()) }
} 