package com.example.pokedex.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private val client = OkHttpClient.
        Builder().
        build()

    private val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>):T{
        return retrofit.create(service)
    }

}