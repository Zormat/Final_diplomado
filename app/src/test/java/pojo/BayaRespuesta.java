package pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BayaRespuesta {

    @SerializedName("results")
    @Expose
    private ArrayList<Baya> results = null;




    public ArrayList<Baya> getResults() {
        return results;
    }

}
