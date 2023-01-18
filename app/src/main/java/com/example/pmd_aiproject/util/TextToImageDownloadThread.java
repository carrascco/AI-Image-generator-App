package com.example.pmd_aiproject.util;

import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;

import androidx.annotation.Nullable;

import com.example.pmd_aiproject.MainActivity;
import com.example.pmd_aiproject.TextToImageActivity;
import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.ImageDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

public class TextToImageDownloadThread implements Runnable {

    private TextToImageActivity act;
    private String prompt;
    private String username;

    public TextToImageDownloadThread(TextToImageActivity act,String prompt, String username){ this.act=act;  this.prompt=prompt; this.username = username;}
    public void run(){

        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                act.prepareForRequest();
            }
        });
        Response r = NetUtil.generateImagePet(prompt);

        try {
            Bitmap imagenGenerada = NetUtil.getURLBitmap(r.getData());
            ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
            imagenGenerada.compress(Bitmap.CompressFormat.PNG, 0 , baos);

            DBHelper db= DBHelper.DBfabric(act.getApplicationContext());
            ImageDB.postImage(db.getWritableDatabase(),username,prompt,getDateTime(),baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);

    }
}
