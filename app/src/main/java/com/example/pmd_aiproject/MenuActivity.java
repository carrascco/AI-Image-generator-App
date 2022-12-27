package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button textoAImg=findViewById(R.id.btn_pedir_texto);
        textoAImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirTextoAImagen=new Intent(MenuActivity.this, TextToImageActivity.class);
                //   abrirSegundaActividad.putExtra(NOMBRE_PARAMETRO_1, ((EditText)findViewById(R.id.txt_main_phone)).getText().toString());
                startActivity(abrirTextoAImagen);
            }
        });

        Button imagenAImg=findViewById(R.id.btn_pedir_img);
        textoAImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirImagenAImagen=new Intent(MenuActivity.this, ImageToImageActivity.class);
                //   abrirSegundaActividad.putExtra(NOMBRE_PARAMETRO_1, ((EditText)findViewById(R.id.txt_main_phone)).getText().toString());
                startActivity(abrirImagenAImagen);
            }
        });


    }
}