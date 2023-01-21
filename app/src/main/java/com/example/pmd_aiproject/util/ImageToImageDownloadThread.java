package com.example.pmd_aiproject.util;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.pmd_aiproject.ImageToImageActivity;
import com.example.pmd_aiproject.MainActivity;
import com.example.pmd_aiproject.TextToImageActivity;
import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.ImageDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

public class ImageToImageDownloadThread implements Runnable {

    private ImageToImageActivity act;
    private Bitmap bitmap;
    private String username;

    public static DBHelper db;

    NotificationHandler handler;

    public ImageToImageDownloadThread(ImageToImageActivity act, Bitmap bitmap, String username, Context ctx){ this.act=act;  this.bitmap=bitmap; this.username = username; this.handler = new NotificationHandler(ctx);}
    public void run(){

        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                act.prepareForRequest();
            }
        });
        Response r = NetUtil.generateImagePet(bitmap);


        try {
            Bitmap imagenGenerada = NetUtil.getURLBitmap(r.getData());
            ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
            imagenGenerada.compress(Bitmap.CompressFormat.PNG, 0 , baos);


            db= DBHelper.DBfabric(act.getApplicationContext());
            int idImagen=ImageDB.postImage(db.getWritableDatabase(),username,"",getDateTime(),baos.toByteArray());

            sendNotification(true,idImagen);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendNotification (boolean priority, int idImagen) {
        Notification.Builder res = handler.createNotification("Imagen generada correctamente, "+this.username, "", priority, idImagen, "", username);
        handler.getManager().notify(idImagen, res.build());
        handler.createGroup(priority);

    }


    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);

    }
}
