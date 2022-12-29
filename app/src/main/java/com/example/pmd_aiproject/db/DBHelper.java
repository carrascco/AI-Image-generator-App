package com.example.pmd_aiproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="openai.db";
    private static final int DATABASE_VERSION = 1;

    private static DBHelper dbHelper;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static DBHelper DBfabric(@Nullable Context context) {
        if(dbHelper==null)
            dbHelper = new DBHelper(context);
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //TO-DO Añadir las distintas relaciones que haya, de momento solo nos hace falta imagen creada por usuario

        sqLiteDatabase.execSQL("CREATE TABLE user (_name TEXT, password TEXT, access_key TEXT, PRIMARY KEY (_name))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
