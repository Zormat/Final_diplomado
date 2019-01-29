package com.example.zormat.pokedex.pojo;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movimiento {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
