package com.example.zormat.pokedex.pokeapi;

import com.example.zormat.pokedex.pojo.BayaRespuesta;
import com.example.zormat.pokedex.pojo.ItemRespuesta;
import com.example.zormat.pokedex.pojo.MovimientoRespuesta;
import com.example.zormat.pokedex.pojo.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface PokeApiServicio {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
    Call<PokemonRespuestaCapturados> obtenerListaPokemonCapturados(@Query("limit") int limit);


    @GET("move")
    Call<MovimientoRespuesta> obtenerListaMovimientos(@Query("limit") int limit, @Query("offset") int offset);

    @GET("berry")
    Call<BayaRespuesta> obtenerListaBayas(@Query("limit") int limit, @Query("offset") int offset);

    @GET("item")
    Call<ItemRespuesta> obtenerListaItems(@Query("limit") int limit, @Query("offset") int offset);
}
