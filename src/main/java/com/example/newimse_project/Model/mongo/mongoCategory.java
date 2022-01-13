package com.example.newimse_project.Model.mongo;

import com.mongodb.DBObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class mongoCategory {
    @Id
    private String id;
    private String title;
    private String description;
    private List<DBObject> books;

    public mongoCategory(String title, String description) {
        this.title = title;
        this.description = description;
        this.books = new ArrayList<DBObject>();
    }

}
