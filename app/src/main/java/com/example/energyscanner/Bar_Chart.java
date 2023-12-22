package com.example.energyscanner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

//import org.jetbrains.*;
//import org.jetbrains.kotlinx.datetime.LocalDateTime;
//import org.jetbrains.kotlinx.datetime.TimeZone;
//import org.jetbrains.kotlinx.datetime.toLocalDate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bar_Chart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    DatabaseManager dbManager;
    ArrayList<Date> dateStringList;
    ArrayList<String> valuesStrinList;
    int indexdate1, indexdate2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        dbManager = DatabaseManager.getInstance(this);
        try {
            dbManager.open();
        }catch (Exception e){
            e.printStackTrace();
        }
        dateStringList = new ArrayList<>();
        valuesStrinList = new ArrayList<>();
        Cursor cursor = dbManager.fetch();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String value = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ENERGY_VAL));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ENERGY_DATE));
                Date dateFormatted = new Date(Long.parseLong(date));
                dateStringList.add(dateFormatted);
                valuesStrinList.add(value);

            }while(cursor.moveToNext());
        }


        Spinner spinner1 = findViewById(R.id.spinnerData1);
        Spinner spinner2 = findViewById(R.id.spinnerData2);
        ArrayAdapter<Date> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dateStringList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner1.setSelection(0);
        spinner2.setSelection(dateStringList.size() - 1);

        findViewById(R.id.buttonFromBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Pie_Bar.class));
            }
        });

        findViewById(R.id.buttonBar2).setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                BarChart barChart2 = findViewById(R.id.barChart);

                barChart2.clear();

                ArrayList<BarEntry> changeNumber = new ArrayList<>();

                if( indexdate1 > indexdate2) {
                    int buffer = indexdate2;
                    indexdate2 = indexdate1;
                    indexdate1 = buffer;
                    spinner1.setSelection(indexdate1);
                    spinner2.setSelection(indexdate2);
                }

                for ( int i = indexdate1; i <= indexdate2; i++){
                  changeNumber.add(new BarEntry(dateStringList.get(i).getDate(), Integer.valueOf(valuesStrinList.get(i))));
                }

                BarDataSet barDataSet2 = new BarDataSet(changeNumber, "Random Numbers");
                barDataSet2.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet2.setValueTextColor(Color.BLACK);
                barDataSet2.setValueTextSize(16f);

                BarData barData = new BarData(barDataSet2);


                barChart2.setFitBars(true);
                barChart2.setData(barData);
                barChart2.getDescription().setText("changed values");
                barChart2.animateY(2000);
                barChart2.getBarData().setBarWidth(0.5f);
                XAxis xAxis = barChart2.getXAxis();
                xAxis.setGranularity(3f); // Устанавливаем шаг между значениями оси X
                xAxis.setGranularityEnabled(true); // Включаем функцию шага
            }
        });

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> values = new ArrayList<>();
        for ( int i = 0; i < dateStringList.size(); i++){

            values.add(new BarEntry(dateStringList.get(i).getDate(), Integer.valueOf(valuesStrinList.get(i))));
        }

        BarDataSet barDataSet = new BarDataSet(values, "Values");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar chart for electricity values");
        barChart.animateY(2000);
        barChart.getBarData().setBarWidth(0.5f);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f); // Устанавливаем шаг между значениями оси X
        xAxis.setGranularityEnabled(true); // Включаем функцию шага
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Date selectedItem = (Date)parent.getItemAtPosition(position);
        int bufer = 0;
        for ( int i = 0; i < dateStringList.size(); i++){
            if (dateStringList.get(i) == selectedItem) bufer = i;
        }
        if (parent.getId() == R.id.spinnerData1)
        {
            indexdate1 = bufer;
        } else indexdate2 = bufer;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinnerData1) indexdate1 = 0;
        else  indexdate2 = dateStringList.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Long> datesToLongs(List<Date> dates) {
        return dates.stream().map(java.util.Date::getTime).collect(Collectors.toList());
    }


}