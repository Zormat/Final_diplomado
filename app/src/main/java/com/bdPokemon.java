package com;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zormat.pokedex.pojo.Pokemon;

import java.util.ArrayList;

public class bdPokemon  extends SQLiteOpenHelper {
private static final String Nombre_BD ="pokedex.bd";
private static final int Version_BD=1;
private static final String  Tabla_Poke_Capturados="Create Table pokemoncapturado (ID TEXT)";

    public bdPokemon(Context context) {
        super(context, Nombre_BD, null, Version_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabla_Poke_Capturados);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", Tabla_Poke_Capturados));
        db.execSQL(Tabla_Poke_Capturados);
    }
public void capturarPokeon(String idpoke){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("Insert into pokemoncapturado(ID) VALUES('%s');", idpoke);
        db.execSQL(query);
}

    public void liberarpokemon(String idpoke){

        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("delete from  pokemoncapturado where id='%s';", idpoke);
        db.execSQL(query);
    }



    public Cursor listacapturados(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM  pokemoncapturado" , null);
        return data;
    }

    public boolean estaCapturado(String idpoke){
        SQLiteDatabase db = getReadableDatabase();
        String query =  String.format("SELECT * FROM pokemoncapturado where  id='%s';", idpoke);
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.getCount() <=0){
            cursor.close();
            return false;


        }

        cursor.close();
        return  true;


    }




}
