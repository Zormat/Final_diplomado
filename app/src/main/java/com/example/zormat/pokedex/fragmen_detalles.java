package com.example.zormat.pokedex;


import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zormat.pokedex.models.masdetallepoke;
import com.example.zormat.pokedex.pokeapi.GetPokemonDataService;
import com.example.zormat.pokedex.pokeapi.pokereference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class fragmen_detalles extends Fragment {

    // Variables holds the current Pokemon
    private String mPokemonId;
    private masdetallepoke mPokemon;

    private static final String ARG_POKEMON_ID = "3";

   /* public static fragmen_detalles newInstance(String pokemonId) {
        Bundle args = new Bundle();
        args.putString(ARG_POKEMON_ID, pokemonId);

        fragmen_detalles fragment = new fragmen_detalles();
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Sets the Pokemon layout.

        mPokemonId = getArguments().getString("idpoke");
        View v = inflater.inflate(R.layout.fragment_detallespoke, container, false);

        // Retrofit sets up the connection to PokeAPI
        Retrofit retrofit = pokereference.getRetrofitInstance();
        GetPokemonDataService pokemonDataService = retrofit.create(GetPokemonDataService.class);

        Call<masdetallepoke> pokemonCall = pokemonDataService.getPokemon(mPokemonId);

        Log.d("..." + mPokemonId, "3");

        mPokemon = new masdetallepoke();

     //   final Picasso picasso = new Picasso.Builder(getActivity()).downloader(new OkHttp3Downloader(retrofit.callFactory())).build();

        // Set Views to variables
        final ImageView mPokemonImage = (ImageView) v.findViewById(R.id.pokemonImage);
        final TextView mPokemonName = (TextView) v.findViewById(R.id.pokemonName);
        final TextView mPokemonType = (TextView) v.findViewById(R.id.pokemonType);
        final TextView mPokemonWeight = (TextView) v.findViewById(R.id.pokemonWeight);
        final TextView mPokemonHeight = (TextView) v.findViewById(R.id.pokemonHeight);
        final TextView mPokemonHP = (TextView) v.findViewById(R.id.pokemonHP);
        final TextView mPokemonAttack = (TextView) v.findViewById(R.id.pokemonAttack);
        final TextView mPokemonDefense = (TextView) v.findViewById(R.id.pokemonDefense);
        final TextView mPokemonSpAttack = (TextView) v.findViewById(R.id.pokemonSpAttack);
        final TextView mPokemonSpDefense = (TextView) v.findViewById(R.id.pokemonSpDefense);
        final TextView mPokemonSpeed = (TextView) v.findViewById(R.id.pokemonSpeed);


        // Execute the call and, if it succeeds, set it to a Pokemon object
        //mPokemon = pokemonCall.execute().body();
        pokemonCall.enqueue(new Callback<masdetallepoke>() {
            @Override
            public void onResponse(Call<masdetallepoke> call, Response<masdetallepoke> response) {
                mPokemon = response.body();
                Log.d("Response String", mPokemon.pokemon_name);

                mPokemonName.setText(mPokemon.pokemon_name);

                String type = mPokemon.pokemon_types.get(0).pokemon_types.pokemon_type_name;

                if(mPokemon.pokemon_types.size() == 2) {
                    type += "/" + mPokemon.pokemon_types.get(1).pokemon_types.pokemon_type_name;
                }

                mPokemonType.setText(type);

                mPokemonWeight.setText("" + mPokemon.pokemon_weight + "kg");

                mPokemonHeight.setText("" + mPokemon.pokemon_height + "m");

                mPokemonHP.setText("Vida: " + mPokemon.pokemon_stats.get(0).pokemon_base_stat);

                mPokemonAttack.setText("Ata: " + mPokemon.pokemon_stats.get(1).pokemon_base_stat);

                mPokemonDefense.setText("Def: " + mPokemon.pokemon_stats.get(2).pokemon_base_stat);

                mPokemonSpAttack.setText("Ata esp: " + mPokemon.pokemon_stats.get(3).pokemon_base_stat);

                mPokemonSpDefense.setText("Def sp: " + mPokemon.pokemon_stats.get(4).pokemon_base_stat);

                mPokemonSpeed.setText("Velocidad: " + mPokemon.pokemon_stats.get(5).pokemon_base_stat);
                Glide.with(getContext().getApplicationContext()).load("https://raw.githubusercontent.com/tdmalone/pokecss-media/master/graphics/pokemon/ani-front/"+mPokemon.pokemon_name+".gif").centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(mPokemonImage);


            }

            @Override
            public void onFailure(Call<masdetallepoke> call, Throwable t) {
                Log.d("Call Failure", "A call was made, but it failed");
            }


        });

        Log.d("After Call Block", "Code after call block is running.");

        //ImageView mPokemonImage = (ImageView) v.findViewById(R.id.pokemonImage);
        //mPokemonImage.setImageResource(mPokemon.getImage());


        return v;
    }


}
