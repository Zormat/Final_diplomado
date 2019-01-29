package com.example.zormat.pokedex.pokeapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokereference {

    public static Retrofit retrofit;
    public static final String BASE_URL = "https://pokeapi.co/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
