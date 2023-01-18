package com.example.pmd_aiproject.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.pmd_aiproject.model.Image;
import com.example.pmd_aiproject.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ImageDB {

    public static AtomicInteger id = new AtomicInteger(0);

    public static List<Image> getUserByUsername (SQLiteDatabase db, String username){
        String query = "SELECT * FROM image WHERE user_name =?";
        Cursor c = db.rawQuery(query, new String[]{""+username});

        List<Image> res = new LinkedList<Image>();

        if(c.moveToNext()){
            Integer id = c.getInt(0);
            String user_name = c.getString(1);
            String prompt = c.getString(2);
            String fecha = c.getString(3);
            byte[] image = c.getBlob(4);
            String key = c.getString(2);
            res.add(new Image(id,user_name,prompt,fecha,image));
        }
        return res;

    }

    public static List<Image> getAll(SQLiteDatabase db){
        String query = "SELECT * FROM image";
        Cursor c = db.rawQuery(query,null);
        List<Image> res = new LinkedList<Image>();
        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String user_name = c.getString(1);
            String prompt = c.getString(2);
            String fecha = c.getString(3);
            byte[] image = c.getBlob(4);
            res.add(new Image(id,user_name,prompt,fecha,image));
        }
        return res;
    }

    public static void postImage(SQLiteDatabase db,String user_name,String prompt,String fecha, byte[] image) {

        ContentValues values = new ContentValues();

        values.put("user_name", user_name);
        values.put("prompt", prompt);
        values.put("fecha", fecha);
        values.put("image", image);
        long insertId = db.insert("image", null, values);



    }

}
