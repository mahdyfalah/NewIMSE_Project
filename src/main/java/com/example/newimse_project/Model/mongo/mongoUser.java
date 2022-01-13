package com.example.newimse_project.Model.mongo;

import com.mongodb.DBObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document(collection = "User")
public class mongoUser {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private List<DBObject> bookList;
    private List<DBObject> comments;


    public mongoUser(String fullName, String email, String phoneNumber, String address) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.bookList = new ArrayList<DBObject>();
        this.comments = new ArrayList<DBObject>();
    }
    public mongoUser() {
    }
}
