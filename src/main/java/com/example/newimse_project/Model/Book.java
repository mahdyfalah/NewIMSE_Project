package com.example.newimse_project.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    private String Name;
    private String Link;
    private String writersName;




    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book_Publisher> bookPublishers = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book_User_Comment> book_user_comments = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book_User_Read> book_user_reads = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book_Ncategory> book_ncategories = new ArrayList<>();

//    @OneToMany(mappedBy="chapterNO", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Chapters> chapters = new ArrayList<>();

    public Book()
    {}

    public Book(String name, String link, String writersName) {
        Name = name;
        Link = link;
        this.writersName = writersName;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getWritersName() {
        return writersName;
    }

    public void setWritersName(String writersName) {
        this.writersName = writersName;
    }
}
