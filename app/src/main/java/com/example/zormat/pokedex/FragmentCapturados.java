package com.example.zormat.pokedex;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bdPokemon;
import com.example.zormat.pokedex.pokeapi.PokeApiServicio;
import com.example.zormat.pokedex.pokeapi.PokemonRespuestaCapturados;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class FragmentCapturados extends Fragment {


    private RecyclerView recyclerPokemon;
    private com.example.zormat.pokedex.Adaptadores.PokemonAdapter adapter;


    public FragmentCapturados() {
        // Required empty public constructor
    }
    bdPokemon localDB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        localDB  = new bdPokemon(getContext());
        View vista = inflater.inflate(R.layout.fragment_fragment1, container,false);

        recyclerPokemon = vista.findViewById(R.id.recView);
        adapter = new com.example.zormat.pokedex.Adaptadores.PokemonAdapter(getContext());
        recyclerPokemon.setAdapter(adapter);
        recyclerPokemon.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerPokemon.setLayoutManager(layoutManager);

        obtenerGeneracion();

        return vista;
    }

    private void obtenerGeneracion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeApiServicio service = retrofit.create(PokeApiServicio.class);
        Call<PokemonRespuestaCapturados> call = service.obtenerListaPokemonCapturados(2);

        call.enqueue(new Callback<PokemonRespuestaCapturados>() {
            @Override
            public void onResponse(Call<PokemonRespuestaCapturados> call, Response<PokemonRespuestaCapturados> response) {
                if(response.isSuccessful()){
                    PokemonRespuestaCapturados p = response.body();
                    // ArrayList<Pokemon> listaPokemon = p.getResults();
                    ArrayList<com.example.zormat.pokedex.models.Pokemon> listaPokemon = p.getResults();

                    adapter.addListaPokemon(listaPokemon);
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuestaCapturados> call, Throwable t) {
                Toast toast1 =
                        Toast.makeText(getContext(),
                                "Error", Toast.LENGTH_SHORT);

                toast1.show();
            }


        });
    }


}
