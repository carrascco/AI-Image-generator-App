package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pmd_aiproject.util.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TextToImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_image);

        FloatingActionButton returnFromRegister=findViewById(R.id.btn_return_fromText);
        returnFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverDeRegistro=new Intent(TextToImageActivity.this, MenuActivity.class);
                //abrirSegundaActividad.putExtra("NOMBRE_PARAMETRO_1", "texto");
                startActivity(volverDeRegistro);
            }
        });

    }

    private static void postmanPostOpenAI(String prompt) {
        try {
            URL url = new URL("https://api.openai.com/v1/images/generations");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.addRequestProperty("Authorization", "Bearer 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");
            conn.addRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{\"prompt\": \""+prompt+"\"}";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }/*
            int responseStatus = conn.getResponseCode();
            System.out.println(responseStatus);

            String responseMessage = conn.getResponseMessage();
            System.out.println(responseMessage);
            */
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            String json = "";
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                json += line;
            }
            reader.close();
             Gson gson = new GsonBuilder().setPrettyPrinting().create();
             Response r = gson.fromJson(json, Response.class);
            // System.out.println(r);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}