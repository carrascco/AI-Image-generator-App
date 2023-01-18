package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ImageToImageActivity extends AppCompatActivity {

    public static final String NOMBRE_PARAMETRO_1="usuario:";
    public static final String NOMBRE_PARAMETRO_2="key:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_image);

        Intent i=getIntent();

        String username = i.getStringExtra(NOMBRE_PARAMETRO_1);
        String userKey = i.getStringExtra(NOMBRE_PARAMETRO_2);

        FloatingActionButton returnFromRegister=findViewById(R.id.btn_return_fromImage);
        returnFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverDeRegistro=new Intent(ImageToImageActivity.this, MenuActivity.class);
                volverDeRegistro.putExtra(NOMBRE_PARAMETRO_1, username);
                volverDeRegistro.putExtra(NOMBRE_PARAMETRO_2, userKey);
                startActivity(volverDeRegistro);
            }
        });
    }
}