package com.example.ramersoft;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramersoft.model.GlobalConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cyd.awesome.material.AwesomeText;

public class Registration extends AppCompatActivity implements View.OnClickListener {



    EditText name, quali, addrs;
    Button register;
    String sname,squali,saddrs;
    Dialog dialog;
    JSONArray jArr ;
    JSONObject jObj ;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_user);

        name = (EditText) findViewById(R.id.ename);
        quali = (EditText) findViewById(R.id.equali);
        addrs = (EditText) findViewById(R.id.eaddrs);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);




    }

    @Override
    public void onClick(View view) {


        int i = view.getId();
        if (i == R.id.register) {
            if (checkallfeilds()) {
                Registeruser();
            }

        } else if (i == R.id.toolbar) {
            onBackPressed();

        } else if (i == R.id.login) {
            finish();

        }

    }


    private boolean checkallfeilds() {

        boolean flag = true;

        sname = name.getText().toString();
        squali = addrs.getText().toString();
        saddrs = quali.getText().toString();




        if (TextUtils.isEmpty(sname)) {
            flag = false;
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(squali)) {
            flag = false;
            Toast.makeText(this,"Please enter your Qualification",Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(saddrs)) {
            flag = false;
            Toast.makeText(this,"Please enter your address",Toast.LENGTH_SHORT).show();
        }


        return flag;


    }


    private void Registeruser() {

         dialog = new ProgressDialog(Registration.this,R.style.MyAlertDialogStyle);
        dialog.setTitle("Registering User");
        dialog.show();
        makeJSON();



    }


    public void makeJSON() {
         jArr = new JSONArray();
         jObj = new JSONObject();
        try {

            jObj.put("user_name", name.getText().toString());
            jObj.put("user_qualification",  quali.getText().toString());
            jObj.put("user_address",  addrs.getText().toString());



            jArr.put(jObj);
            System.out.println(jObj.toString());
            dialog.dismiss();

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }

        GlobalConfig config = new GlobalConfig();
        config.addJSONObject(jObj);

        Intent  i=new Intent(this,HomeScreen.class);
        startActivity(i);
        //return jArr;
    }



}
