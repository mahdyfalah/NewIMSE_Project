package com.example.newimse_project.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Book_User_Read")
public class Book_User_Read {


    @EmbeddedId
    private Book_User_ReadId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "read_date")
    private LocalDate readDate;



    public Book_User_Read(Book book, User user, LocalDate readDate,Long read_id) {
        this.id = new Book_User_ReadId(book.getBookID(), user.getUserID(),read_id);
        this.book = book;
        this.user = user;
        this.readDate = readDate;
    }

    public Book_User_Read()
    {}

    public Book_User_ReadId getId() {
        return id;
    }

    public void setId(Book_User_ReadId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getReadDate() {
        return readDate;
    }

    public void setReadDate(LocalDate readDate) {
        this.readDate = readDate;
    }
}
