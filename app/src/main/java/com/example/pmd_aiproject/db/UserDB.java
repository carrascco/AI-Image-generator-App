package com.example.pmd_aiproject.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pmd_aiproject.model.User;

import java.util.LinkedList;
import java.util.List;

public class UserDB {



    public static User getUserByName (SQLiteDatabase db, String name){
        String query = "SELECT * FROM user WHERE _name =?";
        Cursor c = db.rawQuery(query, new String[]{""+name});
        User res = null;
        if(c.moveToNext()){
            String username = c.getString(0);
            String password = c.getString(1);
            res = new User(username, password);
        }
        return res;

    }

    public static List<User> getAll(SQLiteDatabase db){
        String query = "SELECT * FROM user";
        Cursor c = db.rawQuery(query,null);
        List<User> res = new LinkedList<User>();
        while (c.moveToNext()){
            String pass = c.getString(1);
            String name = c.getString(0);
            res.add(new User(name, pass));
        }
        return res;
    }


    public static void postUser(SQLiteDatabase db, String username, String password, String key) {

        ContentValues values = new ContentValues();
        values.put("_name", username);
        values.put("access_key", key);
        values.put("password", password);
        long insertId = db.insert("user", null, values);
    }

}
