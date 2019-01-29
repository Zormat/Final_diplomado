package pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {


    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }



}
