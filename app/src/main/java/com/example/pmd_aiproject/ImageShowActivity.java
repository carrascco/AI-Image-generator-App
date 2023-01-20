package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmd_aiproject.db.ImageDB;
import com.example.pmd_aiproject.util.TextToImageDownloadThread;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ImageShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        Intent i=getIntent();
        Integer idImagen=i.getIntExtra("Image",0);
        String prompt=i.getStringExtra("prompt");
        String username=i.getStringExtra("username:");

        byte[] imageRes= ImageDB.getBlobById(TextToImageDownloadThread.db.getReadableDatabase(),idImagen);
        ImageView img= (ImageView) findViewById(R.id.id_img_shown);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageRes, 0, imageRes.length);
        img.setImageBitmap(bitmap);
        TextView txtDesc=findViewById(R.id.id_txt_descrip_image);
        txtDesc.setText("Descripcion: \""+ prompt+"\"");

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