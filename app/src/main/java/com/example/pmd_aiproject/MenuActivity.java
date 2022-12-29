package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuActivity extends AppCompatActivity {
    public static final String NOMBRE_PARAMETRO_1="usuario:";
    public static final String NOMBRE_PARAMETRO_2="key:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent i=getIntent();
        String username = i.getStringExtra(NOMBRE_PARAMETRO_1);
        String userKey = i.getStringExtra(NOMBRE_PARAMETRO_2);


        FloatingActionButton returnFromRegister=findViewById(R.id.btn_return_fromMenu);
        returnFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverDeRegistro=new Intent(MenuActivity.this, MainActivity.class);

                startActivity(volverDeRegistro);
            }
        });


        Button textoAImg=findViewById(R.id.btn_pedir_texto);
        textoAImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTextoAImagen=new Intent(MenuActivity.this, TextToImageActivity.class);
                abrirTextoAImagen.putExtra(NOMBRE_PARAMETRO_1, username);
                abrirTextoAImagen.putExtra(NOMBRE_PARAMETRO_2, userKey);
                startActivity(abrirTextoAImagen);
            }
        });

        Button imagenAImg=findViewById(R.id.btn_pedir_img);
        imagenAImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirImagenAImagen=new Intent(MenuActivity.this, ImageToImageActivity.class);
                abrirImagenAImagen.putExtra(NOMBRE_PARAMETRO_1, username);
                abrirImagenAImagen.putExtra(NOMBRE_PARAMETRO_2, userKey);
                startActivity(abrirImagenAImagen);
            }
        });


    }
}