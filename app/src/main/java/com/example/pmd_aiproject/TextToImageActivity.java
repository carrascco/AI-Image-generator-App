package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmd_aiproject.util.Response;
import com.example.pmd_aiproject.util.TextToImageDownloadThread;
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

    public static final String NOMBRE_PARAMETRO_1="usuario:";
    public static final String NOMBRE_PARAMETRO_2="key:";

    public static int counter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_image);

        Intent i=getIntent();

        String username = i.getStringExtra(NOMBRE_PARAMETRO_1);
        String userKey = i.getStringExtra(NOMBRE_PARAMETRO_2);

        FloatingActionButton returnFromRegister=findViewById(R.id.btn_return_fromText);
        returnFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverDeRegistro=new Intent(TextToImageActivity.this, MenuActivity.class);
                volverDeRegistro.putExtra(NOMBRE_PARAMETRO_1, username);
                volverDeRegistro.putExtra(NOMBRE_PARAMETRO_2, userKey);
                startActivity(volverDeRegistro);
            }
        });



        Button generateImage =findViewById(R.id.btn_txt2img_generar);

        generateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txt = findViewById(R.id.id_txt_prompt);
                // ejecutar peticion en Thread y cuando termine ejecutar updateView en el Thread principal y mandar notificacion de que la peticion ha terminado, con la imagen que esta ha generado
                TextToImageDownloadThread txt2imgPet = new TextToImageDownloadThread(TextToImageActivity.this,txt.getText().toString(),username, TextToImageActivity.this);
                Thread th = new Thread(txt2imgPet);
                th.start();

                Toast.makeText(TextToImageActivity.this, "Peticion realizada, le llegara una notificacion cuando la imagen se haya generado",Toast.LENGTH_SHORT).show();

            }
        });
    }
    //Cambio de void a Response para recibir la url invocandole un getData al Response que devuelva
    private static Response postmanPostOpenAI(String prompt) {
        Response r=null;
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
            }
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
             r = gson.fromJson(json, Response.class);
            // System.out.println(r);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return r;
    }

    public void prepareForRequest() {

    }
}