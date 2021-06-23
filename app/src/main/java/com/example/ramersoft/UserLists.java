package com.example.ramersoft;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserLists extends AppCompatActivity implements View.OnClickListener {

    Userlist adapter;
    String jsonArray;
    Context context;
    JSONArray array;
    List<JSONArray> demoarray;
    JSONObject jsonObject;
    RecyclerView userlist1;
    MainActivity mainActivity;
    ImageView backimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist);
        userlist1=(RecyclerView) findViewById(R.id.userlist);
        TextView nouser=(TextView)findViewById(R.id.nouser);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("UserList");
        myToolbar.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        context = this.getApplicationContext();


        mainActivity=new MainActivity();

        demoarray=mainActivity.getjsonarray();
        if(demoarray.size()>0){
            userlist1.setVisibility(View.VISIBLE);
            nouser.setVisibility(View.GONE);
        }



        try {
            /*array = new JSONArray(jsonArray);
            System.out.println(array.toString(2));
            for (int i = 0; i < array.length(); i++) {
                 jsonObject = array.getJSONObject(i);

            }*/
            if(demoarray.size()>0) {
                adapter = new Userlist(this, demoarray);
                userlist1.setAdapter(adapter);
                userlist1.setLayoutManager(new LinearLayoutManager(this));
                adapter.notifyDataSetChanged();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {


        int i = view.getId();

         if (i == R.id.toolbar) {
            onBackPressed();

        }

    }
}
