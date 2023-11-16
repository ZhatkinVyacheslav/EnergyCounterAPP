package com.example.energyscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Bar_Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

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
                changeNumber.add(new BarEntry(2014, 120));
                changeNumber.add(new BarEntry(2015, 170));
                changeNumber.add(new BarEntry(2016, 190));
                changeNumber.add(new BarEntry(2017, 102));
                changeNumber.add(new BarEntry(2018, 155));
                changeNumber.add(new BarEntry(2019, 100));
                changeNumber.add(new BarEntry(2020, 150));
                changeNumber.add(new BarEntry(2021, 150));
                changeNumber.add(new BarEntry(2022, 100));


                BarDataSet barDataSet2 = new BarDataSet(changeNumber, "Random Numbers");
                barDataSet2.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet2.setValueTextColor(Color.BLACK);
                barDataSet2.setValueTextSize(16f);

                BarData barData = new BarData(barDataSet2);



                barChart2.setFitBars(true);
                barChart2.setData(barData);
                barChart2.getDescription().setText("Bar chart Example");
                barChart2.animateY(2000);
            }
        });

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(2014, 120));
        visitors.add(new BarEntry(2015, 470));
        visitors.add(new BarEntry(2016, 490));
        visitors.add(new BarEntry(2017, 502));
        visitors.add(new BarEntry(2018, 555));
        visitors.add(new BarEntry(2019, 600));
        visitors.add(new BarEntry(2020, 650));
        visitors.add(new BarEntry(2021, 550));
        visitors.add(new BarEntry(2022, 500));

        BarDataSet barDataSet = new BarDataSet(visitors, "Random Numbers");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);



        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar chart Example");
        barChart.animateY(2000);


    }
}