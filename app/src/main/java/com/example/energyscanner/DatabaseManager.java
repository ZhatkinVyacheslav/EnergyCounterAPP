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
    private static DatabaseManager instance;

    private DatabaseManager(Context ctx){
        context = ctx;
    }
    public static synchronized DatabaseManager getInstance(Context ctx) {
        if (instance == null) {
            instance = new DatabaseManager(ctx);
        }
        return instance;
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
        contentValues.put(DatabaseHelper.ENERGY_DATE, date.getTime());
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch(){
        String[] columns = new String[] { DatabaseHelper.CONTENT_ID, DatabaseHelper.ENERGY_VAL, DatabaseHelper.ENERGY_DATE };
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
        contentValues.put(DatabaseHelper.ENERGY_DATE, date.getTime());
        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.CONTENT_ID + "=" + _id, null);
        return ret;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.CONTENT_ID + "=" + _id, null);
    }
}
