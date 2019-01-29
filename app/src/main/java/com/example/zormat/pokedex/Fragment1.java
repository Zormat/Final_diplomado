package com.example.zormat.pokedex;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.zormat.pokedex.pojo.PokemonRespuesta;
import com.example.zormat.pokedex.pokeapi.PokeApiServicio;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Fragment1 extends Fragment {


    private RecyclerView recyclerPokemon;
    private com.example.zormat.pokedex.Adaptadores.PokemonAdapter adapter;


    public Fragment1() {
        // Required empty public constructor
    }


    private Animator spruce;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View vista = inflater.inflate(R.layout.fragment_fragment1, container,false);

        recyclerPokemon = vista.findViewById(R.id.recView);
        adapter = new com.example.zormat.pokedex.Adaptadores.PokemonAdapter(getContext());
        recyclerPokemon.setAdapter(adapter);
        recyclerPokemon.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerPokemon.setLayoutManager(layoutManager);



        obtenerGeneracion();

      //  LinearLayoutManager = new LinearLayoutManager()


        return vista;
    }

    private void obtenerGeneracion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeApiServicio service = retrofit.create(PokeApiServicio.class);
        Call<PokemonRespuesta> call = service.obtenerListaPokemon(151,0);

        call.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if(response.isSuccessful()){
                    PokemonRespuesta p = response.body();
                    ArrayList<com.example.zormat.pokedex.models.Pokemon> listaPokemon = p.getResults();

                    adapter.addListaPokemon(listaPokemon);
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                Toast toast1 =
                        Toast.makeText(getContext(),
                                "Error", Toast.LENGTH_SHORT);

                toast1.show();
            }
        });
    }


}
