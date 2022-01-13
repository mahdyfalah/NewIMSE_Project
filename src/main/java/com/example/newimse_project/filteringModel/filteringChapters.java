package com.example.newimse_project.filteringModel;

import com.example.newimse_project.Model.CompositChapterID;
import com.mongodb.DBObject;

import java.util.List;

public class filteringChapters {


    private Long Id;
    private String title;
    private String mongoID;

    public filteringChapters(String title,  Long chapterNo) {
        this.Id = chapterNo;
        this.title = title;
    }




    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
