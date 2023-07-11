package com.example.deber_2_consumir_api_restful_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void processFinish(String result) throws JSONException {

        String lstEmail="";
        JSONArray listaJson = new JSONArray(result);

        for (int i=0; i<listaJson.length();i++)
        {
            JSONObject email=listaJson.getJSONObject(i);
            lstEmail=lstEmail+"\n"+email.getString("email").toString();
        }

        TextView txtmensaje = (TextView)findViewById(R.id.txtmostrar);
        txtmensaje.setText("Este es el resultado"+"\n"
                +lstEmail);

    }

    public void consulta (View view) {

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }
}