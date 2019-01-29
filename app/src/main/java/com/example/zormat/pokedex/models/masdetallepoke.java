package com.example.zormat.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class masdetallepoke {

    @SerializedName("name")
    public String pokemon_name;

    @SerializedName("types")
    public ArrayList<PokemonType> pokemon_types;

    @SerializedName("height")
    public int pokemon_height;

    @SerializedName("weight")
    public int pokemon_weight;

    @SerializedName("stats")
    public ArrayList<PokemonStat> pokemon_stats;

  //  @SerializedName("sprites")
   // public Sprite pokemon_sprites;

    public class PokemonType {
        @SerializedName("type")
        public Type pokemon_types;

        public class Type {
            @SerializedName("name")
            public String pokemon_type_name;
        }
    }

    public class PokemonStat {
        @SerializedName("stat")
        public Stat pokemon_stats;

        @SerializedName("base_stat")
        public int pokemon_base_stat;

        public class Stat {
            @SerializedName("name")
            public String pokemon_stat_name;
        }
    }
}
