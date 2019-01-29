package com.example.zormat.pokedex.Adaptadores;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdPokemon;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zormat.pokedex.MainActivity;
import com.example.zormat.pokedex.R;
import com.example.zormat.pokedex.fragmen_detalles;
import com.example.zormat.pokedex.models.pokemon_enpoke;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.Locale;


public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> implements View.OnClickListener, TextToSpeech.OnInitListener {
    private int lastPosition = -1;
    private ArrayList<com.example.zormat.pokedex.models.Pokemon> datos;
    private Context context;
    bdPokemon localDB;
    private TextToSpeech tts;
    private pokemon_enpoke  poke_capturado;

    public PokemonAdapter(Context context){
        this.context = context;
        datos = new ArrayList<com.example.zormat.pokedex.models.Pokemon>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);


        return new ViewHolder(view);


    }
private String numeropoke;
    @Override
    public void onBindViewHolder(PokemonAdapter.ViewHolder holder, int position) {


        int pos;
        localDB  = new bdPokemon(this.context);
        com.example.zormat.pokedex.models.Pokemon p = datos.get(position);
        holder.nombreTextView.setText(p.getName().toUpperCase());
        holder.numeroTextView.setText(String.valueOf(p.getNumber()));
            pos = holder.getAdapterPosition();

        Glide.with(context)
                .load("https://raw.githubusercontent.com/tdmalone/pokecss-media/master/graphics/pokemon/ani-front/" + p.getName() + ".gif")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);



        setAnimation(holder.itemView,  position);



        if(localDB.estaCapturado(String.valueOf(holder.numeroTextView.getText())))  holder.lotenemos.setImageResource(R.drawable.pokecapturado);
      else  if(!localDB.estaCapturado(String.valueOf(holder.numeroTextView.getText())))  holder.lotenemos.setImageResource(R.drawable.pokesincapturar);

        //holder.fotoImageView.setOnClickListener(this);




    }

    private void setAnimation(View itemView, int position) {

        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void addListaPokemon(ArrayList<com.example.zormat.pokedex.models.Pokemon> listaPokemon) {
        datos.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {


    }
    private void setFragment(Fragment fragment) {
        MainActivity myActivity = (MainActivity)context;
        FragmentTransaction fragmentTransaction = myActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
             Locale locSpanish = new Locale("spa", "MEX");
            int result = tts.setLanguage(locSpanish);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context.getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context.getApplicationContext(), "Init failed", Toast.LENGTH_SHORT).show();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private TextView numeroTextView;
        private ImageView lotenemos;



        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            numeroTextView = (TextView) itemView.findViewById(R.id.numero);
            lotenemos = (ImageView) itemView.findViewById(R.id.capturado);


            fotoImageView.setOnClickListener(this);
            lotenemos.setOnClickListener(this);




        }


        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.fotoImageView) {

            final fragmen_detalles capturados = new fragmen_detalles();


            Bundle bundle = new Bundle();
            bundle.putString("idpoke", String.valueOf(numeroTextView.getText()));
            capturados.setArguments(bundle);
            setFragment(capturados);
            }


            else if  (v.getId() == R.id.capturado) {


                if (!localDB.estaCapturado(String.valueOf(numeroTextView.getText()))) {
                    localDB.capturarPokeon(String.valueOf(String.valueOf(numeroTextView.getText())));
                    lotenemos.setImageResource(R.drawable.pokecapturado);
                  Toast.makeText(context, nombreTextView.getText() + " " + " fue capturado", Toast.LENGTH_SHORT).show();
                    //tts.speak("Has capturado un charizard", TextToSpeech.QUEUE_FLUSH, null);



                } else {

                    localDB.liberarpokemon(String.valueOf(String.valueOf(numeroTextView.getText())));
                    lotenemos.setImageResource(R.drawable.pokesincapturar);
                   // tts.speak("Has capturado un charizard", TextToSpeech.QUEUE_FLUSH, null);


                    Toast.makeText(context, nombreTextView.getText() + " " + " fue liberado", Toast.LENGTH_SHORT).show();


                }

            }
        }
    }
}
