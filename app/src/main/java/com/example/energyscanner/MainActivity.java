package com.example.energyscanner;

import static com.example.energyscanner.R.id.buttonDBtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    DatabaseManager dbManager;
    DataGenerator gen;
    Timer timer = new Timer();
    TimerTask timerTask;
    int timeDelayMilliseconds = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Database Manager
        dbManager = DatabaseManager.getInstance(this);
        try {
            dbManager.open();
        }catch (Exception e) {
            e.printStackTrace();
        }

        // Create DataGenerator as placeholder for creating data.
        // Generates data in milliseconds placed in the constructor.
        // Then place generated data with a bit latency just to be sure to have no conflict.
        gen = new DataGenerator(timeDelayMilliseconds);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                insertGenData();
            }
        };
        timer.schedule(timerTask, 0, timeDelayMilliseconds + 250);

        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Bar_Chart.class));
            }
        });

        findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Pie_Bar.class));
            }
        });

        findViewById(buttonDBtesting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DatabaseActivity.class));
            }
        });
    }

    // Always initialize object with context of a function to have an instance of this object
    // Then open the database to make in not null
    // Then work with any functions
    private void insertGenData(){
        DatabaseManager db = DatabaseManager.getInstance(this);
        try {
            db.open();
        }catch (Exception e) {
            e.printStackTrace();
        }
        db.insert(gen.returnValue(), gen.returnDate());
    }

}