package com.example.zormat.pokedex;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zormat.pokedex.models.Pokemon;
import com.example.zormat.pokedex.models.masdetallepoke;
import com.example.zormat.pokedex.pokeapi.GetPokemonDataService;
import com.example.zormat.pokedex.pokeapi.pokereference;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Retrofit;

import static android.app.PendingIntent.getActivity;
import static android.content.Context.MODE_PRIVATE;

public class ListaPokemonAdapter  extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> implements TextToSpeech.OnInitListener {
  /*  private List<PokemonCard> pokemonCardList;
    private List<PokemonCard> savedPokemonList;*/
    private View.OnClickListener listener;
    private ArrayList<Pokemon> dataset;
    private masdetallepoke mPokemon;

    private HomeFragment context;
    public ListaPokemonAdapter(HomeFragment contex){
        this.context = contex;
        dataset = new ArrayList<>();

    }

   // private List<PokemonCard> originalList;

    Dialog myDialog;
    
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);

        return new ViewHolder(view);

    }

    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());
        holder.numeroTextView.setText(p.getNumero());

        Glide.with(context).load("http://pokeapi.co/media/sprites/pokemon/"+p.getNumber()+".png").centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.fotoImageView);


        //   holder.fotoImageView.setOnClickListener(this);


    }





    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon){
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();


    }


    private TextToSpeech tts;

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            //  Locale locSpanish = new Locale("spa", "MEX");
            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(context.getContext(), "Language not supported", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(context.getContext(), "Init failed", Toast.LENGTH_SHORT).show();}


    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView fotoImageView;
        private TextView nombreTextView;
        private TextView numeroTextView;


        public ViewHolder(View itemView) {
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            numeroTextView = (TextView) itemView.findViewById(R.id.numero);
            fotoImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
         /*   Intent intent = new Intent(getActivity(MainActivity) , infopokemon.class);
            startActivity(intent);

*/
            // tts.speak(mPokemon.pokemon_name  +"this pokémon weighs "+ String.valueOf(mPokemon.pokemon_weight)+"and heighs"+ mPokemon.pokemon_height + "his attack power is up to " + mPokemon.pokemon_stats.get(1).pokemon_base_stat, TextToSpeech.QUEUE_FLUSH, null);


         // Intent detail = new Intent(context, infopokemon.class);

           // Intent detail = new Intent(contex, dealloc.class);

       //    detail.putExtra("numero",numeroTextView.getText().toString());
        //  detail.putExtra("id", imagen.getId());
          //  context.startActivity(detail);
        }

     
        
      /*  public void setFav(Context context){
            SharedPreferences settings = context.getSharedPreferences("pokedex", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            Set<String> caught = settings.getStringSet("caught", new HashSet<String>());

            List<PokemonCard> newList = new ArrayList<>();
            if (originalList == null)
                originalList = pokemonCardList;

            if (originalList != null & originalList.size() > 0) {
                for (final PokemonCard g : savedPokemonList) {
                    for(String search: caught) {
                        if(g.getName().toLowerCase().contains(search.toLowerCase())) {
                            newList.add(g);
                            break;
                        }
                    }
                }
            }
            Log.i("HELP","unique, set<String> : final size : "+ newList.size());
            pokemonCardList = newList;
            originalList = newList;
            notifyDataSetChanged();
        }

*/

    }
}

