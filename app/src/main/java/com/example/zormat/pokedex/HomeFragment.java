package com.example.zormat.pokedex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zormat.pokedex.models.Pokemon;
import com.example.zormat.pokedex.models.PokemonRepuesta;
import com.example.zormat.pokedex.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter  listaPokemonAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = vista.findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);

        // GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        retrofit = new Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
        obtenerDatos();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void obtenerDatos() {

        PokeapiService service =  retrofit.create(PokeapiService.class);
        Call<PokemonRepuesta> pokemonRepuestaCall = service.obtenerListaPokemon();


        //service.obtenerListaPokemon(151,0);
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
