package com.example.rickandmortycompose.app

import android.app.Application
import com.example.rickandmortycompose.data.serviceLocator.dataModule
import com.example.rickandmortycompose.ui.serviceLocator.uiModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(dataModule, uiModule)
        }
    }
}