package com.example.pmd_aiproject.model;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("url")
    private String url;


    public Data(String url) {
        this.url=url;
    }

    public String getUrl() {
        return url;
    }
}
