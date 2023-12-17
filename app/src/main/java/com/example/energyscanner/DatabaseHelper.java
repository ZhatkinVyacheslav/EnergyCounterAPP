package com.example.energyscanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "EnergyDB";
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "ENERGY";
    static final String CONTENT_ID = "_ID";
    static final String ENERGY_VAL = "Energy Value";
    static final String Energy_DATE = "Energy Date";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( " + CONTENT_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + ENERGY_VAL + " INTEGER, " + Energy_DATE
            + " DATE PRIMARY KEY AUTOINCREMENT " + " ); ";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }
}
