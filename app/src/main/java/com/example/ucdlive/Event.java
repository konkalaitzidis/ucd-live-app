package com.example.ucdlive;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Event implements Serializable, Comparable<Event>{

    private String name;
    private String brief;
    private String description;
    private Date date;

    public Event() {
    }

    public Event(String name, String brief, String description, Date date){
        this.name = name;
        this.brief = brief;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getBrief() {
        return brief;
    }

    @Override
    public int compareTo(Event o) {
        return -this.date.compareTo(o.getDate());
    }
}
