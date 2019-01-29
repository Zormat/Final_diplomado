package com.example.zormat.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class itememnu extends AppCompatActivity {
    private String name;
    private int iconId;


    public itememnu(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itememnu);
    }
}
