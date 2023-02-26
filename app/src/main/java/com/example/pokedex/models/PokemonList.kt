package com.example.pokedex.models


import com.google.gson.annotations.SerializedName

data class PokemonList(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("image")
    var image:String,
)