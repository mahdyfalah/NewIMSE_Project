package com.example.newimse_project.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name = "Book_User_Comment")
public class Book_User_Comment {

    @EmbeddedId
    private Book_User_CommentId id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long comment_Id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_date")
    private LocalDate commentDate;


    @Column(name = "comment_text")
    private String commentText;


    public Book_User_Comment(Book book, User user, LocalDate commentDate,String commentText,Long comment_Id) {
        this.id = new Book_User_CommentId(book.getBookID(), user.getUserID(),comment_Id);
        this.book = book;
        this.user = user;
        this.commentDate = commentDate;
        this.commentText = commentText;

    }


//    public Book_User_CommentId getId() {
//        return id;
//    }
//
//    public void setId(Book_User_CommentId id) {
//        this.id = id;
//    }

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

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Book_User_Comment()
    {}

//
//    public Long getComment_Id() {
//        return comment_Id;
//    }
//
//    public void setComment_Id(Long comment_Id) {
//        this.comment_Id = comment_Id;
//    }
}
