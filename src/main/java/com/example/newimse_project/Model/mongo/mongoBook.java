package com.example.newimse_project.Model.mongo;

import com.mongodb.DBObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Book")
public class mongoBook {
    @Id
    private String id;
    private String name;
    private String link;
    private String writersName;

    List<DBObject> comments;
    List<DBObject> userList;
    List<DBObject> publishers;
    List<DBObject> categories;
    List<DBObject> chapters;

    public mongoBook(String name, String link, String writersName) {
        this.name = name;
        this.link = link;
        this.writersName = writersName;
        this.comments = new ArrayList<DBObject>();
        this.userList = new ArrayList<DBObject>();
        this.publishers = new ArrayList<DBObject>();
        this.categories = new ArrayList<DBObject>();
        this.chapters= new ArrayList<DBObject>();
    }

    public mongoBook() {
    }
}
