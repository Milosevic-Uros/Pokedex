package com.example.pokedex.api

import com.example.pokedex.models.Pokemon
import com.example.pokedex.models.PokemonList
import com.example.pokedex.models.Type
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon?limit=1000000&offset=0")
    fun getPokemonList(): Call<PokemonList>

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name")name:String): Call<Pokemon>

}