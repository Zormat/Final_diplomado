package com.example.zormat.pokedex;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.example.zormat.SharedPreference;


public class MainActivity extends AppCompatActivity {
    SharedPreference sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPreference(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.night);
        }
        else  if(sharedpref.loadNightModeState()==false)  setTheme(R.style.AppTheme);


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final Fragment1 homeFragment = new Fragment1();
        final FragmentCapturados prueba = new FragmentCapturados();
        final fragment_opciones detalles = new fragment_opciones();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, homeFragment)
                .commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.inicio) {
                    setFragment(homeFragment);
                    return true;
                }
                else if (id == R.id.config){
                   setFragment(detalles);


                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.home);


    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }



}
