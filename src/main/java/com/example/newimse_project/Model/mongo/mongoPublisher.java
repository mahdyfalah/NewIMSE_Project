package com.example.newimse_project.Model.mongo;

import com.mongodb.DBObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class mongoPublisher {
    @Id
    private String id;
    private String fullName;
    private String email;
    List<DBObject> books;

    public mongoPublisher(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
        this.books = new ArrayList<DBObject>();
    }
}
