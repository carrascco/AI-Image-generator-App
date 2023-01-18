package com.example.pmd_aiproject;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.ImageDB;
import com.example.pmd_aiproject.model.ImageAdapter;


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

        DBHelper db = DBHelper.DBfabric(HistorialActivity.this);

        ListView lv = findViewById(R.id.lv_historial_images);

        lv.setAdapter(new ImageAdapter(ImageDB.getUserByUsername(db.getReadableDatabase(),username)));






    }
}