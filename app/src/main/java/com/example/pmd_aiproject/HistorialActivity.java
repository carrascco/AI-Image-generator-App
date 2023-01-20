package com.example.pmd_aiproject;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.ImageDB;
import com.example.pmd_aiproject.model.ImageAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HistorialActivity extends AppCompatActivity {

    public static final String NOMBRE_PARAMETRO_1="usuario:";
    public static final String NOMBRE_PARAMETRO_2="key:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);


        Intent i=getIntent();
        String username = i.getStringExtra(NOMBRE_PARAMETRO_1);
        String userKey = i.getStringExtra(NOMBRE_PARAMETRO_2);
        FloatingActionButton returnFromRegister=findViewById(R.id.btn_historial_return);
        returnFromRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent volverDeImagen=new Intent(HistorialActivity.this, MenuActivity.class);
                volverDeImagen.putExtra("usuario:", username);
                startActivity(volverDeImagen);
            }
        });

        DBHelper db = DBHelper.DBfabric(HistorialActivity.this);

        ListView lv = findViewById(R.id.lv_historial_images);

        lv.setAdapter(new ImageAdapter(ImageDB.getUserByUsername(db.getReadableDatabase(),username)));






    }
}