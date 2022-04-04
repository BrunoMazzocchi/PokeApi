package com.example.pokeapp
import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("name") var name: String?,
    @SerializedName("height") var height: Float?,
    @SerializedName("weight") var weight: Float?,
    @SerializedName("id") var id: Int?,
    @SerializedName("type") var type: String?
)
