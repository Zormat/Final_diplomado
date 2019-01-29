package com.example.zormat.pokedex.models;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;




public class pokemon_enpoke {

    public static final String MyPREFERENCES = "MyPrefs";
    public SharedPreferences sharedPreferences;

    public pokemon_enpoke(Context ctx ){
        sharedPreferences = ctx.getSharedPreferences(MyPREFERENCES,ctx.MODE_PRIVATE);
    }

    public boolean AddFavoriteMovie(String favoriteItem){
        //Get previous favorite items
        String favoriteList = getStringFromPreferences("","favorites");
        // Append new Favorite item
        if(favoriteList!=null){
            favoriteList = favoriteList+"&"+favoriteItem;
        }else{
            favoriteList = favoriteItem;
        }
        // Save in Shared Preferences
        return putStringInPreferences(favoriteList,"favorites");
    }

    public String[] getFavoriteList(){
        String favoriteList = getStringFromPreferences("","favorites");
        return convertStringToArray(favoriteList);
    }

    public boolean checkInPrefrence(String Details){
        String[] Favorites = getFavoriteList();
        for(int i=0 ; i<Favorites.length ; i++){
            if(Favorites[i].equals(Details) )
                return true;
        }
        return false;
    }

    public void clearPreferences(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public boolean checkIsClear(){
        if (getStringFromPreferences(null,"favorites")==null)
            return true;
        else return false;
    }

    private boolean putStringInPreferences(String value,String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        return true;
    }

    public String getStringFromPreferences(String defaultValue,String key){
        String temp = sharedPreferences.getString(key, defaultValue);
        return temp;
    }

    private String[] convertStringToArray(String str){
        String[] arr = str.split("&");
        return arr;
    }
}