package com.example.newimse_project.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Book_publisher")
public class Book_Publisher {

    @EmbeddedId
    private BookPublisherId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("publisherId")
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    public Book_Publisher(Book book, Publisher publisher, LocalDate publishedDate) {
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

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Book_Publisher() {

    }
}
