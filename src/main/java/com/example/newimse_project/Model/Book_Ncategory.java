package com.example.newimse_project.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Book_Category")
public class Book_Ncategory {

    @EmbeddedId
    private Book_NcategoryId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Ncategory category;


    public Book_Ncategory(Book book, Ncategory category) {
        this.id = new Book_NcategoryId(book.getBookID(), category.getCategoryID());
        this.book = book;
        this.category= category;
    }

    public Book_Ncategory() {

    }

    public Book_NcategoryId getId() {
        return id;
    }

    public void setId(Book_NcategoryId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Ncategory getCategory() {
        return category;
    }

    public void setCategory(Ncategory category) {
        this.category = category;
    }
}
