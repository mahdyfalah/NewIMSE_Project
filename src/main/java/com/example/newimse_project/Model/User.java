package com.example.newimse_project.Model;

import lombok.Data;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UserID;
    private String fullName;
    private String email;
    private String phonenumber;
    private String adress;




    @OneToMany(mappedBy = "user")
    private List<Book_User_Comment> book_user_comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Book_User_Read> book_user_reads = new ArrayList<>();


    public User(String fullName, String email, String phonenumber, String adress) {
        this.fullName = fullName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.adress = adress;
    }

    public User() {

    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
