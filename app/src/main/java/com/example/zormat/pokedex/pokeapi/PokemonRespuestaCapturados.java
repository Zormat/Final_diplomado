package com.example.zormat.pokedex.pokeapi;

import com.example.zormat.pokedex.models.Pokemon;

import java.util.ArrayList;

public class PokemonRespuestaCapturados {
    private ArrayList<Pokemon> results;
    public ArrayList<com.example.zormat.pokedex.models.Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<com.example.zormat.pokedex.models.Pokemon> results){
        this.results = results;

    }
}
