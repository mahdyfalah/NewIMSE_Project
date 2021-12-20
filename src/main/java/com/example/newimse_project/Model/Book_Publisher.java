package com.example.newimse_project.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Book_publisher")
public class Book_Publisher {

    @EmbeddedId
    private BookPublisherId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("publisherId")
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "published_date")
    private Date publishedDate;

    public Book_Publisher(Book book, Publisher publisher, Date publishedDate) {
        this.id = new BookPublisherId(book.getBookID(), publisher.getUserID());
        this.book = book;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    public BookPublisherId getId() {
        return id;
    }

    public void setId(BookPublisherId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Book_Publisher() {

    }
}
