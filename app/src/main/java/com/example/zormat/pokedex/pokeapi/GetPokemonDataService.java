package com.example.zormat.pokedex.pokeapi;

import com.example.zormat.pokedex.models.masdetallepoke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetPokemonDataService {

    @GET("api/v2/pokemon/{name}")
    Call<masdetallepoke> getPokemon(@Path("name") String name);
}
