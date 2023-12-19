package com.example.energyscanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "EnergyDB";
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "ENERGYTABLE";
    static final String CONTENT_ID = "_ID";
    static final String ENERGY_VAL = "En_Value";
    static final String ENERGY_DATE = "En_Date";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( " + CONTENT_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + ENERGY_VAL + " INTEGER, "  + ENERGY_DATE
            + " DATE " + " ); ";
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
