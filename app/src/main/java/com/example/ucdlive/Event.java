package com.example.ucdlive;

import java.time.LocalDateTime;

public class Event {

    private float id;
    private String name;
    private String brief;
    private String description;
    private LocalDateTime date;

    public Event(float id, String name, String brief, String description, LocalDateTime date){
        this.id = id;
        this.name = name;
        this.brief = brief;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getBrief() {
        return brief;
    }
}
