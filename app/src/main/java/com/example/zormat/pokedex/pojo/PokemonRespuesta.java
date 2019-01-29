package com.example.zormat.pokedex.pojo;

import com.example.zormat.pokedex.models.Pokemon;

import java.util.ArrayList;


public class PokemonRespuesta {

    private ArrayList<com.example.zormat.pokedex.models.Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<com.example.zormat.pokedex.models.Pokemon> results) {
        this.results = results;
    }
}
