package com.example.pmd_aiproject.util;
import com.example.pmd_aiproject.model.Data;
import com.google.gson.annotations.SerializedName;

public class Response {
    //	@SerializedName("created")
//	private String created;
    @SerializedName("created")
    private String created;
    @SerializedName("data")
    private Data[] data;


    public String getCreated() {
        return this.created;
    }
    public String getData() {
        return data[0].getUrl();
    }
    //EN data[0] SE ENCUENTRA LA URL DE LA IMAGEN
}
