package com.example.newimse_project.Convert;

import java.math.BigInteger;

public class ConvertObjectComment {
    private String userName;
    private String bookName;
    private String category;
    private String comment;
    private BigInteger comment_id;
    private BigInteger user_id;
    private BigInteger book_id;

    public ConvertObjectComment(String userName, String bookName, String category,
                                String comment,BigInteger comment_id,BigInteger user_id,BigInteger book_id) {
        this.userName = userName;
        this.bookName = bookName;
        this.category = category;
        this.comment = comment;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public BigInteger getComment_id() {
        return comment_id;
    }

    public void setComment_id(BigInteger comment_id) {
        this.comment_id = comment_id;
    }

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public BigInteger getBook_id() {
        return book_id;
    }

    public void setBook_id(BigInteger book_id) {
        this.book_id = book_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
