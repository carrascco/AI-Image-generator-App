package com.example.pmd_aiproject.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pmd_aiproject.model.User;

public class UserDB {



    public static User getUserByName (SQLiteDatabase db, String name){
        String query = "SELECT * FROM user WHERE _name =?";
        Cursor c = db.rawQuery(query, new String[]{""+name});
        User res = null;
        if(c.moveToNext()){
            String username = c.getString(0);
            String password = c.getString(1);
            String key = c.getString(2);
            res = new User(username, password,key);
        }
        return res;

    }


    public static void postUser(SQLiteDatabase db, String username, String password1, String key) {
        String query = "INSERT INTO user VALUES(?,?,?)";
        Cursor c = db.rawQuery(query, new String[]{username, password1, key});


    }

}
