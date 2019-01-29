package pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemRespuesta {

    @SerializedName("results")
    @Expose
    private ArrayList<Item> results = null;


    public ArrayList<Item> getResults() {
        return results;
    }

}
