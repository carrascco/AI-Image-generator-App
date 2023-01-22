package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.ImageDB;
import com.example.pmd_aiproject.util.DownloadTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class ImageShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        Intent i=getIntent();
        Integer idImagen=i.getIntExtra("Image",0);
        String prompt=i.getStringExtra("prompt");
        String username=i.getStringExtra("username:");

        byte[] imageRes= ImageDB.getBlobById(DBHelper.DBfabric(ImageShowActivity.this.getApplicationContext()).getReadableDatabase(),idImagen);
        ImageView img= (ImageView) findViewById(R.id.id_img_shown);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageRes, 0, imageRes.length);
        img.setImageBitmap(bitmap);
        TextView txtDesc=findViewById(R.id.id_txt_descrip_image);
        if(!prompt.isEmpty())
            txtDesc.setText("Descripcion: \""+ prompt+"\"");
        else{
            txtDesc.setText("Imagen modificada");
        }
        FloatingActionButton downloadBtn=findViewById(R.id.btn_download_image);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Nombre del archivo de la imagen descargada
                String fileName = "image"+(new Random().nextInt(100)+1)+".jpg";
                // Directorio donde se guardará la imagen descargada
                String directory = "images";
                // Bitmap que deseas descargar

                // Crea una nueva tarea para descargar el bitmap
                new DownloadTask(ImageShowActivity.this, bitmap, fileName, directory);
            }
        });


        FloatingActionButton returnFromRegister=findViewById(R.id.btn_return_fromImageShow);
        returnFromRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent volverDeImagen=new Intent(ImageShowActivity.this, MenuActivity.class);
                volverDeImagen.putExtra("usuario:", username);
                startActivity(volverDeImagen);
            }
        });

    }
}