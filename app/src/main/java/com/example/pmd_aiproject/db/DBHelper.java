package com.example.pmd_aiproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="openai.db";
    private static final int DATABASE_VERSION = 8;

    private static DBHelper dbHelper;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static DBHelper DBfabric(@Nullable Context context) {
        if(dbHelper==null) {
            dbHelper = new DBHelper(context);
            SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();

            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS image (_id INTEGER PRIMARY KEY AUTOINCREMENT,user_name TEXT, prompt TEXT, fecha TEXT,  image BLOB," +  "FOREIGN KEY(user_name) REFERENCES user(_name))");
        }

        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //TO-DO Añadir las distintas relaciones que haya, de momento solo nos hace falta imagen creada por usuario
      //  sqLiteDatabase.execSQL("DROP TABLE user");
        //sqLiteDatabase.execSQL("DROP TABLE image");

        sqLiteDatabase.execSQL("CREATE TABLE user (_name TEXT, password TEXT, access_key TEXT, PRIMARY KEY (_name))");

        sqLiteDatabase.execSQL("CREATE TABLE image (_id INTEGER PRIMARY KEY AUTOINCREMENT,user_name TEXT, prompt TEXT, fecha TEXT,  image BLOB," +
                "FOREIGN KEY(user_name) REFERENCES user(_name))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
