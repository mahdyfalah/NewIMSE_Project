package com.example.newimse_project.Model.mongo;

import com.mongodb.DBObject;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class mongoCommentReport {
    private String user_fullName;
    private String book_name;
    private String comment_text;
    private List<DBObject> categories;

    public mongoCommentReport(String user_fullName, String book_name, String comment_text) {
        this.user_fullName = user_fullName;
        this.book_name = book_name;
        this.comment_text = comment_text;
    }

    public mongoCommentReport() {
    }
}
