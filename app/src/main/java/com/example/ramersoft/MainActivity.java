package com.example.ramersoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] users = { "English", "हिंदी", "தமிழ்" };
    String currentLanguage = "en", currentLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spin = (Spinner) findViewById(R.id.spinner1);

        currentLanguage = getIntent().getStringExtra(currentLang);
        if(currentLanguage!=null) {

           // Toast.makeText(this, currentLanguage, Toast.LENGTH_SHORT).show();

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                setLocale("en");
                break;

            case 1:
                setLocale("hi");
                break;
            case 2:
                setLocale("kr");
                break;

        }
    }
    public void setLocale(String localeName) {
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        Button login = (Button) findViewById(R.id.button);
        if(localeName.equals("hi")) {

            editText.setHint(R.string.login1);
            editText2.setHint(R.string.password1);
            login.setText(R.string.signin1);
        }
        else if(localeName.equals("kr")){
            editText.setHint(R.string.login0);
            editText2.setHint(R.string.password0);
            login.setText(R.string.signin0);
        }
        else{
            editText.setHint(R.string.login);
            editText2.setHint(R.string.password);
            login.setText(R.string.signin);
        }
           /*Locale  myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);*/
      /*  Intent refresh = new Intent(this, MainActivity.class);
        refresh.putExtra(currentLang, localeName);
        startActivity(refresh);
*/


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}