package com.example.newimse_project.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Chapter")
public class Chapters implements Serializable {


    private String Title;

    @EmbeddedId
    private CompositChapterID Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable=false, updatable=false)
    private Book book;


    public Chapters()
    {}

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Chapters(String title, Long Id, Book book) {
        Title = title;
        this.Id = new CompositChapterID(Id,book.getBookID());
        this.book= book;

    }


    public CompositChapterID getId() {
        return Id;
    }

    public void setId(CompositChapterID id) {
        Id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
