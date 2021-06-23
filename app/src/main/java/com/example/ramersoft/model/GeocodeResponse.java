package com.example.ramersoft.model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.ramersoft.MainActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodeResponse {
    JSONObject jObj;
    JSONArray jsonArray;
    public  void getAddress(final String locationAddress, final Context context, Handler handler){

        Thread thread=new Thread() {
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                double lati=0.0d ;
                double longi=0.0d;
                try {
                    List addressList = geocoder.getFromLocationName(locationAddress, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = (Address) addressList.get(0);

                        lati=address.getLatitude();
                        longi=(address.getLongitude());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (lati!=0.0d && longi!=0.0d) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Address  :" + locationAddress + "\n" + result;
                        bundle.putDouble("lat",lati);
                        bundle.putDouble("long",longi);

                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }

  /*  @ElementList(entry = "result", inline = true)
    List<result> results;
    @Element(name="status")
    public class status {
        List<result> results;
    }
    static class result {
        @ElementList(entry = "geometry", inline = true)
        List<geometry> geometry;
    }
    static class geometry {
        @ElementList(entry = "location", inline = true)
        List<geometry> geometry;
    }*/

}
