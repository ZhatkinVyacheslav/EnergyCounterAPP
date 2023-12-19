package com.example.energyscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.sql.Date;

public class DatabaseActivity extends AppCompatActivity {
    EditText editID;
    EditText editEnergyValue;
    EditText editDate;

    DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        editID = (EditText) findViewById(R.id.editTextID);
        editEnergyValue = (EditText) findViewById(R.id.editTextEnergyValue);
        editDate = (EditText) findViewById(R.id.editEnergyDate);

        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onInsertBtnClick(View v){
        dbManager.insert(Integer.valueOf(editEnergyValue.getText().toString()), java.sql.Date.valueOf(editDate.getText().toString()));
    }

    public void onFetchBtnClick(View v){
        Cursor cursor = dbManager.fetch();

        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT_ID));
                @SuppressLint("Range") String Value = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ENERGY_VAL));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ENERGY_DATE));
                Log.i("DATABASE_TAG", "I HAVE READ ID: " + ID + " ENERGY VALUE: " + Value
                        + " DATE: " + date);
            }while(cursor.moveToNext());
        }
    }

    public void onUpdateBtnClick(View v){
        dbManager.update(Long.parseLong(editID.getText().toString()),Integer.valueOf(editEnergyValue.getText().toString()), java.sql.Date.valueOf(editDate.getText().toString()));
    }

    public void onDeleteBtnClick(View v){
        dbManager.delete(Long.parseLong(editID.getText().toString()));
    }

}
