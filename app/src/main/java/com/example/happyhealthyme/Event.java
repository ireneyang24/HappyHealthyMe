package com.example.happyhealthyme;

import android.text.format.DateFormat;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.UUID;

public class Event {

    private UUID id;
    private String title;
    private String date;

    public Event(String newTitle, String newDate)
    {
        id = UUID.randomUUID();
        title = newTitle;
        date = newDate;
    }

    public UUID getID()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String newDate)
    {
        String convertedDate = dateString(newDate);
        date = convertedDate;
    }
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    public String dateString(String currentDate)
    {
        Scanner scan = new Scanner(currentDate);
        int year = scan.nextInt();
        int month = scan.nextInt();
        int day = scan.nextInt();
//        String result = year + " " + month + " " + day;

        String monthString = "invalid";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthList = dfs.getMonths();

        if(month >= 0 && month <= 11)
        {
            monthString = monthList[month];
        }

        String result = monthString + " " + day + ", " + year;
//
        return result;
    }
}
