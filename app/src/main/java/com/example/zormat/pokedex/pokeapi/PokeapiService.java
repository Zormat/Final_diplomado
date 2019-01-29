package com.example.zormat.pokedex.pokeapi;

import com.example.zormat.pokedex.models.PokemonRepuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {
    @GET("pokemon")
    Call<PokemonRepuesta> obtenerListaPokemon();
}

