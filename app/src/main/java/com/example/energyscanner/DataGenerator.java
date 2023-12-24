package com.example.energyscanner;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DataGenerator {
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Date currentDate = new Date(System.currentTimeMillis());
    private Date returnableDate;
    private Calendar calendar = Calendar.getInstance();
    private int max = 25000;
    private int min = 5000;
    private int value;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");


    DataGenerator(int milliseconds, Date lastdate){
        getLastDate(lastdate);
        System.out.println("Current Date is: " + currentDate);
        returnableDate = currentDate;
        timerTask = new TimerTask() {
            @Override
            public void run() {
                generateData();
                System.out.println("Current Date is: " + returnDate() +
                        " Current Value: " + returnValue()+ " Watt");
            }
        };
        timer.schedule(timerTask, 0, milliseconds);
    }

    public void generateData(){
        Random random = new Random();
        value = random.nextInt(max - min + 1) + min;
        java.util.Date utilDate = returnableDate;
        calendar.setTime(utilDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        utilDate = calendar.getTime();
        returnableDate = new java.sql.Date(utilDate.getTime());
    }
    public int returnValue(){
        return value;
    }

    public java.sql.Date returnDate(){
        return returnableDate;
    }

    public void getLastDate(Date date){
        if(date != null){
            currentDate = date;
        }
    }

}
