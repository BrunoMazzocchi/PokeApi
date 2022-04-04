package com.example.pokeapp
import retrofit2.Response
import retrofit2.http.GET
import  retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDatosPokemon(@Url url: String): Response<PokemonModel>
}