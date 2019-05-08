package com.example.happyhealthyme;

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

    public Date getDate()
    {
        return date;
    }

    public void setTitle(String newTitle)
    {
        title = newTitle;
    }


}
