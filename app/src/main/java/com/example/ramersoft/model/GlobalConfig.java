package com.example.ramersoft.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class GlobalConfig {

    JSONArray JsonArray = new JSONArray();

    public JSONArray getJSONArray() {

        return JsonArray;
    }

    public void addJSONObject(JSONObject jsonObject) {

        JsonArray.put(jsonObject);

    }
}