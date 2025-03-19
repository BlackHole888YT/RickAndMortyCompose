package com.example.rickandmortycompose.data.serviceLocator

import com.example.rickandmortycompose.BuildConfig
import com.example.rickandmortycompose.data.api.CharacterApiService
import com.example.rickandmortycompose.data.api.EpisodesApiService
import com.example.rickandmortycompose.data.api.LocationsApiService
import com.example.rickandmortycompose.data.repository.CharactersRepository
import com.example.rickandmortycompose.data.repository.EpisodesRepository
import com.example.rickandmortycompose.data.repository.LocationsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }

    single { get<Retrofit>().create(CharacterApiService::class.java) }
    single { get<Retrofit>().create(EpisodesApiService::class.java) }
    single { get<Retrofit>().create(LocationsApiService::class.java) }

    single { CharactersRepository(get())}
    single { EpisodesRepository(get())}
    single { LocationsRepository(get())}

}

private fun provideOkHttpClient(): OkHttpClient{
    return OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return  Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}