package com.example.newimse_project.Model;

import javax.persistence.*;
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
    private Date readDate;

    public Book_User_Read(Book book, User user, Date readDate) {
        this.id = new Book_User_ReadId(book.getBookID(), user.getUserID());
        this.book = book;
        this.user = user;
        this.readDate = readDate;
    }

    public Book_User_Read()
    {}
}
