package com.example.zormat.pokedex;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.zormat.SharedPreference;


public class fragment_opciones extends Fragment {

    private Switch myswitch;
    SharedPreference sharedpref;

    public fragment_opciones() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        sharedpref = new SharedPreference(getContext());


        View vista = inflater.inflate(R.layout.fragment_opciones, container,false);
        if(sharedpref.loadNightModeState()==true) {

        }
        else {

          /*  MainActivity myActivity = (MainActivity)getActivity();
            Utils.changeToTheme(myActivity, Utils.THEME_WHITE);*/
        }

        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.fragment_opciones);
        myswitch=  vista.findViewById (R.id.myswitch);
        if (sharedpref.loadNightModeState()==true) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                } else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }

            private void restartApp() {
                Intent i = new Intent(getContext(),MainActivity.class);
                startActivity(i);



            }

        });
return  vista;
    }

}










