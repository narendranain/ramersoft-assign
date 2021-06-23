package com.example.ramersoft.model;

import com.google.gson.annotations.SerializedName;

public class MapResult {

    @SerializedName("geometry")
    private String superName;
    private  long lat,lng;

    public MapResult(long lat, long lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public MapResult(String name) {
        this.superName = name;
    }

    public String getName() {
        return superName;
    }
}
