package com.example.ramersoft.Network;

import com.example.ramersoft.Interace.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.XMLFormatter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitClient {

    private static RetrofitClient inatance=null;
    private Api myapi;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private RetrofitClient(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        myapi=retrofit.create(Api.class);
    }
    public static synchronized RetrofitClient getInstance() {
        if (inatance == null) {
            inatance = new RetrofitClient();
        }
        return inatance;
    }

    public Api getMyApi() {
        return myapi;
    }

}
