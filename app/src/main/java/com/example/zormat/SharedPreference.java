package com.example.zormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.zormat.pokedex.pojo.Pokemon;
import com.google.gson.Gson;

public class SharedPreference {
    SharedPreferences mySharedPref ;
    public SharedPreference(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }
    // this method will save the nightMode State : True or False
    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }
    // this method will load the Night Mode State
    public Boolean loadNightModeState (){
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return  state;
    }
}