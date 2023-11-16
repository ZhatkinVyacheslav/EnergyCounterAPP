package com.example.energyscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Pie_Bar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_bar);

        findViewById(R.id.buttonFromPie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Bar_Chart.class));
            }
        });

        PieChart pieChart = findViewById(R.id.pieChart);

        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(420, 2014));
        visitors.add(new PieEntry(550, 2015));
        visitors.add(new PieEntry(580, 2015));
        visitors.add(new PieEntry(650, 2015));
        visitors.add(new PieEntry(600, 2015));
        visitors.add(new PieEntry(120, 2015));
        visitors.add(new PieEntry(413, 2015));
        visitors.add(new PieEntry(400, 2015));
        visitors.add(new PieEntry(600, 2015));

        PieDataSet pieDataSet = new PieDataSet(visitors, "Random Numbers");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Numbers");
        pieChart.setCenterTextSize(24f);
        pieChart.animate();
    }
}