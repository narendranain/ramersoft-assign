package com.example.ramersoft.Interace;

import com.example.ramersoft.model.GeocodeResponse;
import com.example.ramersoft.model.MapResult;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*public interface Api {
    String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/xml?key=AIzaSyB8LOBYmG_kuWNhQ4ldEjQbyEDo_l9puEw&address=%20Amla%20road%20Nalkheda%20465445/";
    @GET("")
    Call<List<MapResult>>  getsuperHeroes();
}*/
public interface Api {
    String BASE_URL = "https://maps.googleapis.com";

    @GET("/maps/api/geocode/xml?key=AIzaSyB8LOBYmG_kuWNhQ4ldEjQbyEDo_l9puEw&address=%20Amla%20road%20Nalkheda%20465445")
    Call<GeocodeResponse> getCityResults();

}
