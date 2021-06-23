package com.example.ramersoft.model;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ramersoft.GetPathFromLocation;
import com.example.ramersoft.HomeScreen;
import com.example.ramersoft.Interace.DirectionPointListener;
import com.example.ramersoft.MainActivity;
import com.example.ramersoft.Network.RetrofitClient;
import com.example.ramersoft.R;
import com.example.ramersoft.model.GlobalConfig;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Address;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapList extends RecyclerView.Adapter<MapList.ViewHolder> {

    private static Context context1;
    private List<JSONArray> eventUserList;
    JSONObject jsonObject;

    JSONArray user;
    GoogleMap map;
    // Provide a suitable constructor (depends on the kind of dataset)
    public MapList(Context context, List<JSONArray> myDataset) {
        eventUserList = myDataset;
        MapList.context1 = context;
        updateList(eventUserList);
    }

    //after search update list
    public void updateList(List<JSONArray> list){

        eventUserList = list;
        //  notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.s_maplist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        // - get element from your dataset at this position

        user = eventUserList.get(position);
        for (int i = 0; i < user.length(); i++) {
            try {
                jsonObject = user.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            holder.name.setText(jsonObject.getString("user_name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.address.setText(jsonObject.optString("user_address").toString());
        holder.view_foreground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {



                     GeocodeResponse geocodeResponse=new GeocodeResponse();
                    geocodeResponse.getAddress(jsonObject.getString("user_address"),context1,new GeoHandler());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private class GeoHandler extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            double lat ,longi;
            switch (msg.what){
                case 1:
                    Bundle bundle=msg.getData();
                    lat=bundle.getDouble("lat");
                    longi=bundle.getDouble("long");
                    break;
                default:{
                    lat=0.0d;
                    longi=0.0d;
                }
            }

            String url = "https://www.google.com/maps/dir/?api=1&destination=" + lat + "," + longi+ "&travelmode=driving";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context1.startActivity(intent);

           /* LatLng source = new LatLng(22.719568, 75.857727);
            LatLng destination = new LatLng(23.836500, 76.240303);

            new GetPathFromLocation(source, destination, new DirectionPointListener() {
                @Override
                public void onPath(PolylineOptions polyLine) {
                    map.addPolyline(polyLine);
                }
            }).execute();*/

         //   Toast.makeText(context1,lat + " ,"+longi,Toast.LENGTH_SHORT).show();
        }
    }

    private void getSuperHeroes() {
        Call<GeocodeResponse> call = RetrofitClient.getInstance().getMyApi().getCityResults();
        call.enqueue(new Callback<GeocodeResponse>() {

            @Override
            public void onResponse(Call<GeocodeResponse> call, Response<GeocodeResponse> response) {

                try {
                    System.out.println("api response" + response.toString());
                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<GeocodeResponse> call, Throwable t) {

            }




        });
    }

    @Override
    public int getItemCount() {
        return eventUserList.size();
    }

    // Return the size of your dataset (invoked by the layout manager)


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        Context context;

        TextView name, qualification, address;
        RelativeLayout view_foreground;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);

            address = (TextView) itemView.findViewById(R.id.address);
            view_foreground = (RelativeLayout) itemView.findViewById(R.id.view_foreground);
        }



    }

    public  String getlatlong(String address){
        String url="https://maps.googleapis.com/maps/api/geocode/xml?address="+address+"&key=AIzaSyB8LOBYmG_kuWNhQ4ldEjQbyEDo_l9puEw";
        return  url;
    }

   /* public void getRoute() throws JSONException {
        MainActivity mainActivity=new MainActivity();
        jarrray=mainActivity.getjObj1();
        for (int i = 0; i < jarrray.size(); i++) {
            JSONArray json = jarrray.get(i);
            JSONObject jsonObject= json.getJSONObject(0);
            try {
                final String lat = jsonObject.getString("Lat");
                final String lng = jsonObject.getString("Lng");
                LatLng latLng = new LatLng(Double.parseDouble(lat.trim()), Double.parseDouble(lng.trim()));
                if (!routeArray.contains(latLng)) {
                    routeArray.add(latLng);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        drawLine(routeArray);
    }*/


   /* public void drawLine(List<LatLng> points) {
        if (points == null) {
            Log.e("Draw Line", "got null as parameters");
            return;
        }

        Polyline line = map.addPolyline(new PolylineOptions().width(3).color(Color.RED));
        line.setPoints(points);
    }*/
}
