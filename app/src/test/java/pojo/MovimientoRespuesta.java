package pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovimientoRespuesta {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("previous")
    @Expose
    private Object previous;
    @SerializedName("results")
    @Expose
    private ArrayList<Movimiento> results = null;
    @SerializedName("next")
    @Expose
    private String next;

    /**
     * No args constructor for use in serialization
     */
    public MovimientoRespuesta() {
    }

    public ArrayList<Movimiento> getResults() {
        return results;
    }
}
