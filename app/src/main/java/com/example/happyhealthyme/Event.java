package com.example.happyhealthyme;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

public class Event {

    private UUID id;
    private String title;
    private Date date;

    public Event(String newTitle, Date newDate)
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
        String dayOfWeek = (String) DateFormat.format("EEEE", date);
        String day = (String) DateFormat.format("dd", date);
        String month = (String) DateFormat.format("MMMM", date);

        String result = dayOfWeek + ", " + month + " " + day;

        return result;
    }

    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    // commented out for testing purposes
//    public String dateString(Date currentDate)
//    {
////        Date date = new Date();
//        String dayOfWeek = (String) DateFormat.format("EEEE", currentDate);
//        String day = (String) DateFormat.format("dd", currentDate);
//        String month = (String) DateFormat.format("MMMM", currentDate);
//
//        String result = dayOfWeek + ", " + month + " " + day;
//
//        return result;
//    }
}
