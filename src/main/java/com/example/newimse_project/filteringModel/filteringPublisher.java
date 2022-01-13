package com.example.newimse_project.filteringModel;

import java.time.LocalDate;
import java.util.Date;

public class filteringPublisher {
    public String Name;
    public Long id;
    public LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
