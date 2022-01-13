package com.example.newimse_project.Convert;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ConvertObjectReading {

    private String userName;
    private String bookName;
    private String publishername;
    private BigInteger read_id;
    private BigInteger user_id;
    private BigInteger book_id;
    public ConvertObjectReading(String userName, String bookName, String publishername,BigInteger read_id,BigInteger user_id,BigInteger book_id) {
        this.userName = userName;
        this.bookName = bookName;
        this.publishername = publishername;
        this.read_id = read_id;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public BigInteger getBook_id() {
        return book_id;
    }

    public void setBook_id(BigInteger book_id) {
        this.book_id = book_id;
    }

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public BigInteger getRead_id() {
        return read_id;
    }

    public void setRead_id(BigInteger read_id) {
        this.read_id = read_id;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername;
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


}
