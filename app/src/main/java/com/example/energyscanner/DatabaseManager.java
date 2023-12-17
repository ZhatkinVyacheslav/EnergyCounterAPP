package com.example.energyscanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx){
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(int value, Date date){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ENERGY_VAL, value);
        contentValues.put(DatabaseHelper.Energy_DATE, date.getTime());
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch(){
        String[] columns = new String[] { DatabaseHelper.CONTENT_ID, DatabaseHelper.ENERGY_VAL, DatabaseHelper.Energy_DATE };
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null,
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, int value, Date date){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ENERGY_VAL, value);
        contentValues.put(DatabaseHelper.Energy_DATE, date.getTime());
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.CONTENT_ID + "=" + _id, null);
        return ret;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.CONTENT_ID + "=" + _id, null);
    }
}
