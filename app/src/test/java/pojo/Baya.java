package pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baya {


    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

}
