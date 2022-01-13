package com.example.newimse_project.Model.mongo;

import com.mongodb.DBObject;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class mongoReadReport {
    private String user_fullName;
    private String book_name;
    private List<DBObject> publishers;

    public mongoReadReport(String user_fullName, String book_name) {
        this.user_fullName = user_fullName;
        this.book_name = book_name;
    }

    public mongoReadReport() {
    }
}
