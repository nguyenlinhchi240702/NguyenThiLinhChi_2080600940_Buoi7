package com.example.nguyenthilinhchi_2080600940_buoi7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "QLSP", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s ("+"%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "%S TEXT, "+ "%s TEXT, " + "%S REAL)","Product","id","name","image","price");
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion != newVersion) {

//drop
            String query = "DROP TABLE Product";
            db.execSQL(query);
//create again
            onCreate(db);
        }
    }
}