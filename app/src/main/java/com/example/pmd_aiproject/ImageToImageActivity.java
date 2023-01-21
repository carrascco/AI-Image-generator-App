package com.example.pmd_aiproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pmd_aiproject.util.ImageToImageDownloadThread;
import com.example.pmd_aiproject.util.TextToImageDownloadThread;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImageToImageActivity extends AppCompatActivity {

    public static final String NOMBRE_PARAMETRO_1="usuario:";
    public static final String NOMBRE_PARAMETRO_2="key:";

    public static final int REQUEST_CODE = 1;

    public static Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_image);

        Intent i=getIntent();

        String username = i.getStringExtra(NOMBRE_PARAMETRO_1);
        String userKey = i.getStringExtra(NOMBRE_PARAMETRO_2);



        Button imagen = findViewById(R.id.btn_img2img_load_image);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if(intent.resolveActivity(getPackageManager())!=null) {
                    startActivityForResult(intent,REQUEST_CODE);


                }else
                    Toast.makeText(ImageToImageActivity.this,"No se abrir imagen",Toast.LENGTH_SHORT).show();
            }
        });

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

        Button pet = findViewById(R.id.btn_img2img_pet);
        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(ImageToImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    ImageToImageDownloadThread img2imgPet = new ImageToImageDownloadThread(ImageToImageActivity.this,bitmap,username, ImageToImageActivity.this);
                    Thread th = new Thread(img2imgPet);
                    th.start();

                    Toast.makeText(ImageToImageActivity.this, "Peticion realizada, le llegara una notificacion cuando la imagen se haya generado",Toast.LENGTH_SHORT).show();
                }else{
                    ActivityCompat.requestPermissions(ImageToImageActivity.this,
                            new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);
                }

            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE &&
                resultCode == Activity.RESULT_OK)
            try{
                InputStream stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                ImageView imageView = findViewById(R.id.iv_img2img_image);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void prepareForRequest() {

    }
}