package com.example.energyscanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DatabaseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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

        dbManager = DatabaseManager.getInstance(this);
        try {
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] data = { "First", "Second"};
        Spinner spinner = findViewById(R.id.Testing);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void onInsertBtnClick(View v){
        dbManager.insert(Integer.valueOf(editEnergyValue.getText().toString()), java.sql.Date.valueOf(editDate.getText().toString()));
    }

    public void onFetchBtnClick(View v){
        Cursor cursor = dbManager.fetch();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT_ID));
                @SuppressLint("Range") String Value = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ENERGY_VAL));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ENERGY_DATE));
                // Преобразуем значение даты в объект класса Date
                Date dateFormatted = new Date(Long.parseLong(date));
                Log.i("DATABASE_TAG", "I HAVE READ ID: " + ID + " ENERGY VALUE: " + Value
                        + " DATE: " + dateFormatted);
            }while(cursor.moveToNext());
        }
    }

    public void onUpdateBtnClick(View v){
        dbManager.update(Long.parseLong(editID.getText().toString()),Integer.valueOf(editEnergyValue.getText().toString()), java.sql.Date.valueOf(editDate.getText().toString()));
    }

    public void onDeleteBtnClick(View v){
        dbManager.delete(Long.parseLong(editID.getText().toString()));
    }

    @SuppressLint("Range")
    public void onDeleteBtnAllClick(View v) {
        Cursor cursor = dbManager.fetch();
        if (cursor.moveToFirst()) {
            do {
               dbManager.delete(cursor.getInt(cursor.getColumnIndex("_ID")));
            } while (cursor.moveToNext());
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
