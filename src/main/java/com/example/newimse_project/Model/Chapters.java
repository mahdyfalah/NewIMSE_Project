package com.example.newimse_project.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Chapter")
public class Chapters implements Serializable {


    private String Title;
    @Id
    private int chapterNO;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID")
    private Book book;


    public Chapters()
    {}

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Chapters(String title, int chapterNO, Book book) {
        Title = title;
        this.chapterNO = chapterNO;
        this.book = book;
    }

    public int getChapterNO() {
        return chapterNO;
    }

    public void setChapterNO(int chapterNO) {
        this.chapterNO = chapterNO;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
