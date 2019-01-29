package com.example.zormat.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.zormat.pokedex.models.Pokemon;
import com.example.zormat.pokedex.models.PokemonRepuesta;
import com.example.zormat.pokedex.pokeapi.PokeapiService;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokemonsCapturados extends AppCompatActivity {
    private static final  String TAG = "POKEDEX";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter  listaPokemonAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons_capturados);
recyclerView = findViewById(R.id.recyclerView);

recyclerView.setAdapter(listaPokemonAdapter);


recyclerView.setHasFixedSize(true);

GridLayoutManager layoutManager = new GridLayoutManager(this,1);
recyclerView.setLayoutManager(layoutManager);
recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

});

        retrofit = new Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        obtenerDatos();


    }

    private void obtenerDatos(){
        PokeapiService service =  retrofit.create(PokeapiService.class);
       Call<PokemonRepuesta> pokemonRepuestaCall = service.obtenerListaPokemon();

        pokemonRepuestaCall.enqueue(new Callback<PokemonRepuesta>() {
            @Override
            public void onResponse(Call<PokemonRepuesta> call, Response<PokemonRepuesta> response) {
                if (response.isSuccessful()){
                    PokemonRepuesta pokemonRepuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRepuesta.getResults();
listaPokemonAdapter.adicionarListaPokemon(listaPokemon);


                }
                else {
                    Log.e(TAG, "oonResponse:" +response.body());

                }
            }

            @Override
            public void onFailure(Call<PokemonRepuesta> call, Throwable t) {
            Log.e(TAG, "onFailure:" + t.getMessage());
            }
        });


    }
}
